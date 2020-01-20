package ijmo.jimbank.banking.jwt

import org.springframework.core.MethodParameter
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails
import org.springframework.web.bind.support.WebDataBinderFactory
import org.springframework.web.context.request.NativeWebRequest
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.method.support.ModelAndViewContainer

class JwtClaimResolver : HandlerMethodArgumentResolver {
    override fun supportsParameter(parameter: MethodParameter): Boolean {
        return parameter.hasParameterAnnotation(CurrentUser::class.java)
    }

    override fun resolveArgument(
        parameter: MethodParameter,
        mavContainer: ModelAndViewContainer?,
        webRequest: NativeWebRequest,
        binderFactory: WebDataBinderFactory?
    ): Any? {
        val authentication: Authentication = SecurityContextHolder.getContext().authentication ?: return null
        val details: Any = authentication.details
        if (details is OAuth2AuthenticationDetails) {
            val decodedDetails = details.decodedDetails as Map<*, *>
            return decodedDetails["user_id"].toString()
        }
        return null
    }
}