package com.example.hexagonal.global.security.jwt

import com.example.hexagonal.adapter.dto.response.TokenResponse
import com.example.hexagonal.domain.User
import com.example.hexagonal.global.security.auth.AuthDetailsService
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.stereotype.Component
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.security.core.userdetails.UserDetails
import java.util.Date
import javax.servlet.http.HttpServletRequest

@Component
class JwtTokenProvider(
    private val jwtProperties: JwtProperties,
    private val authDetailsService: AuthDetailsService,
) {
    fun getToken(email: String): TokenResponse {
        val expiration = jwtProperties.accessExp
        val accessToken: String = generateToken(email, expiration)
        val currentTimeMillis = System.currentTimeMillis()
        val expirationTime = currentTimeMillis + (expiration * 1000)

        return TokenResponse(accessToken = accessToken, expiredAt = expirationTime)
    }



    private fun generateToken(email: String, expiration: Long): String {
        return Jwts.builder().signWith(SignatureAlgorithm.HS256, jwtProperties.secret)
            .setSubject(email)
            .setHeaderParam("typ", "access")
            .setIssuedAt(Date())
            .setExpiration(Date(System.currentTimeMillis() + expiration * 1000))
            .compact()
    }

    fun resolveToken(request: HttpServletRequest): String? {
        val bearer: String? = request.getHeader("Authorization")

        return parseToken(bearer)
    }

    fun parseToken(bearerToken: String?): String? {
        return if (bearerToken != null && bearerToken.startsWith("")) {
            return bearerToken.replace("Bearer", "")
        } else null
    }

    fun authorization(token: String): UsernamePasswordAuthenticationToken {
        return token.let {
            val userDetails: UserDetails = authDetailsService.loadUserByUsername(getTokenSubject(token))
            return UsernamePasswordAuthenticationToken(userDetails, "", userDetails.authorities)
        }
    }

    private fun getTokenSubject(subject: String): String {
        return getTokenBody(subject).subject
    }

    private fun getTokenBody(token: String?): Claims {
        return try {
            Jwts.parser().setSigningKey(jwtProperties.secret)
                .parseClaimsJws(token).body
        } catch (e: Exception) {
            throw Exception("asdf")
        }
    }
}