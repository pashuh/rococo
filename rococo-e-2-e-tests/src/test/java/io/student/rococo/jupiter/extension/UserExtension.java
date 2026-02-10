package io.student.rococo.jupiter.extension;

import com.github.javafaker.Faker;
import io.student.rococo.jupiter.annotation.User;
import io.student.rococo.model.UserJson;
import io.student.rococo.service.UserClient;
import io.student.rococo.service.UserDbClient;
import org.junit.jupiter.api.extension.*;
import org.junit.platform.commons.support.AnnotationSupport;



public class UserExtension implements BeforeEachCallback, ParameterResolver {
    private static final  Faker FAKER = new Faker();
    public static final ExtensionContext.Namespace NAMESPACE = ExtensionContext.Namespace.create(UserExtension.class);
    private final UserClient userClient = new UserDbClient();

    @Override
    public void beforeEach(ExtensionContext context) {
        AnnotationSupport.findAnnotation(
                context.getRequiredTestMethod(),
                User.class
        ).ifPresent(
                anno ->
                    context.getStore(NAMESPACE)
                            .put(context.getUniqueId(),
                                    userClient.createUser(FAKER.name().username(),
                                            FAKER.internet().password()))

        );
    }

    @Override
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        return parameterContext.getParameter().getType().isAssignableFrom(UserJson.class);
    }

    @Override
    public UserJson resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        return extensionContext.getStore(NAMESPACE).get(extensionContext.getUniqueId(), UserJson.class);
}}

