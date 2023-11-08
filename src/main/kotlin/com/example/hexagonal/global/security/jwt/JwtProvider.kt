package com.example.hexagonal.global.security.jwt

import com.example.hexagonal.adapter.dto.response.TokenResponse
import com.example.hexagonal.domain.User
import com.example.hexagonal.global.security.auth.AuthDetailsService
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Component
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import java.util.Base64
import java.util.Date
import javax.servlet.http.HttpServletRequest

@Component
class JwtProvider(
    private val authDetailsService: AuthDetailsService,

    @Value("\${spring.jwt.key}")
    private var key: String,

    @Value("\${spring.jwt.live.accessToken}")
    private val accessTokenTime: Long,

    @Value("\${spring.jwt.live.refreshToken}")
    private val refreshTokenTime: Long) {
    init {
        key = Base64.getEncoder().encodeToString(key.toByteArray())
    }


    fun getToken(user: User): TokenResponse {
        val accessToken = generateAccessToken(user.email)
        return TokenResponse(accessToken, accessTokenTime)
    }

    fun generateAccessToken(email: String): String {
        return generateJwt(email, "access", accessTokenTime)
    }

    private fun generateKey(): ByteArray {
        return Base64.getDecoder().decode(key.toByteArray())
    }

    private fun generateExpirationDate(expirationMillis: Long): Date {
        return Date(System.currentTimeMillis() + expirationMillis * 1000)
    }

    // 수정된 함수명과 파라미터로 변경
    private fun generateJwt(email:String, type:String, expirationMillis :Long):String{
        return Jwts.builder()
            .signWith(SignatureAlgorithm.HS256,generateKey())
            .setSubject(email)
            .claim("type",type)
            .setIssuedAt(Date())
            .setExpiration(generateExpirationDate(expirationMillis))
            .compact()
    }


    fun resolveToken(request: HttpServletRequest?): String? {
        val bearer = request!!.getHeader(HEADER)
        return parseToken(bearer)
    }

    fun authentication(token: String?): Authentication {
        val userDetails = authDetailsService.loadUserByUsername(getTokenSubject(token))
        return UsernamePasswordAuthenticationToken(userDetails, "", userDetails.authorities)
    }

    fun parseToken(bearerToken: String?): String? =
        if (bearerToken != null && bearerToken.startsWith(PREFIX)) bearerToken.replace(PREFIX, "") else null


    private fun getTokenBody(token: String?): Claims {
        return try {
            Jwts.parser().setSigningKey(key.toByteArray())
                .parseClaimsJws(token).body
        } catch (e: Exception) {
            throw Exception()
        }
    }

    private fun getTokenSubject(token: String?): String =
        getTokenBody(token).subject

    companion object {
        private const val HEADER = "Authorization"
        private const val PREFIX = "Bearer"
    }
}