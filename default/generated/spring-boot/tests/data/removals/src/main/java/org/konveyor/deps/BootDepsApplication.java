package org.konveyor.deps;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.config.AbstractRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringApplicationRunListener;
import org.springframework.boot.actuate.autoconfigure.OnEndpointElementCondition;
import org.springframework.boot.actuate.autoconfigure.endpoint.expose.IncludeExcludeEndpointFilter;
import org.springframework.boot.actuate.autoconfigure.metrics.export.dynatrace.DynatraceProperties;
import org.springframework.boot.actuate.autoconfigure.metrics.export.ganglia.GangliaProperties;
import org.springframework.boot.actuate.autoconfigure.web.server.LocalManagementPort;
import org.springframework.boot.actuate.context.properties.ConfigurationPropertiesReportEndpoint;
import org.springframework.boot.actuate.endpoint.annotation.DiscoveredEndpoint;
import org.springframework.boot.autoconfigure.amqp.SimpleRabbitListenerContainerFactoryConfigurer;
import org.springframework.boot.devtools.restart.classloader.RestartClassLoader;
import org.springframework.boot.actuate.env.EnvironmentEndpoint;
import org.springframework.boot.actuate.health.HealthEndpoint;
import org.springframework.boot.actuate.health.HealthEndpointWebExtension;
import org.springframework.boot.actuate.health.ReactiveHealthEndpointWebExtension;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.amqp.AbstractRabbitListenerContainerFactoryConfigurer;
import org.springframework.boot.autoconfigure.amqp.DirectRabbitListenerContainerFactoryConfigurer;
import org.springframework.boot.autoconfigure.amqp.RabbitProperties;
import org.springframework.boot.autoconfigure.amqp.RabbitTemplateConfigurer;
import org.springframework.boot.autoconfigure.condition.ConditionOutcome;
import org.springframework.boot.autoconfigure.flyway.FlywayProperties;
import org.springframework.boot.autoconfigure.jooq.JooqAutoConfiguration;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.boot.autoconfigure.mustache.MustacheProperties;
import org.springframework.boot.autoconfigure.quartz.QuartzDataSourceInitializer;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.boot.autoconfigure.security.saml2.Saml2RelyingPartyProperties;
import org.springframework.boot.autoconfigure.session.JdbcSessionDataSourceInitializer;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.autoconfigure.web.WebProperties;
import org.springframework.boot.autoconfigure.web.reactive.WebFluxProperties;
import org.springframework.boot.autoconfigure.web.reactive.error.AbstractErrorWebExceptionHandler;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcProperties;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.annotation.Configurations;
import org.springframework.boot.context.config.ConfigData;
import org.springframework.boot.context.config.ConfigFileApplicationListener;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.boot.jdbc.DataSourceInitializationMode;
import org.springframework.boot.jdbc.DatabaseDriver;
import org.springframework.boot.loader.Launcher;
import org.springframework.boot.loader.archive.Archive;
import org.springframework.boot.loader.archive.ExplodedArchive;
import org.springframework.boot.loader.archive.JarFileArchive;
import org.springframework.boot.loader.tools.LayoutFactory;
import org.springframework.boot.loader.tools.Packager;
import org.springframework.boot.loader.tools.Repackager;
import org.springframework.boot.logging.log4j2.Log4J2LoggingSystem;
import org.springframework.boot.orm.jpa.hibernate.SpringPhysicalNamingStrategy;
import org.springframework.boot.rsocket.context.LocalRSocketServerPort;
import org.springframework.boot.test.context.assertj.ApplicationContextAssert;
import org.springframework.boot.test.context.assertj.ApplicationContextAssertProvider;
import org.springframework.boot.test.context.assertj.AssertableApplicationContext;
import org.springframework.boot.test.context.runner.AbstractApplicationContextRunner;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;
import org.springframework.boot.test.json.JsonContent;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.boot.web.embedded.netty.SslServerCustomizer;
import org.springframework.boot.web.reactive.error.ErrorAttributes;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.MessageSourceResolvable;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.core.ResolvableType;
import org.springframework.core.env.Environment;
import org.springframework.core.env.StandardEnvironment;
import org.springframework.core.io.Resource;

import java.io.File;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.function.Supplier;
import java.util.jar.Manifest;

@SpringBootApplication
public class BootDepsApplication {

	@LocalServerPort
	@LocalManagementPort
	@LocalRSocketServerPort
	private String port;

	public static void main(String[] args) {
		SpringApplication.run(BootDepsApplication.class, args);
	}

	public void method() throws IOException {
		ConfigFileApplicationListener configFileApplicationListener = new ConfigFileApplicationListener();
		JdbcSessionDataSourceInitializer jdbcSessionDataSourceInitializer;
		QuartzDataSourceInitializer quartzDataSourceInitializer;
		SpringPhysicalNamingStrategy springPhysicalNamingStrategy;
		WebFluxProperties.Cookie cookie = new WebFluxProperties.Cookie();
		WebFluxProperties.Session session = new WebFluxProperties.Session();
		WebFluxProperties.SameSite sameSite = WebFluxProperties.SameSite.NONE;
		IncludeExcludeEndpointFilter.DefaultIncludes defaultIncludes = IncludeExcludeEndpointFilter.DefaultIncludes.JMX;
		int i = defaultIncludes.compareTo(IncludeExcludeEndpointFilter.DefaultIncludes.WEB);
		SslServerCustomizer sslServerCustomizer = new SslServerCustomizer(null, null, null);
		DataSourceInitializationMode mode;

		class ConfigurationPropertiesReportEndpointExtended extends ConfigurationPropertiesReportEndpoint {
			@Override
			public void configureObjectMapper(ObjectMapper mapper) {
				super.configureObjectMapper(mapper);
			}
		}
		ConfigurationPropertiesReportEndpointExtended configurationPropertiesReportEndpointExtended = new ConfigurationPropertiesReportEndpointExtended();
		configurationPropertiesReportEndpointExtended.configureObjectMapper(null);

		Launcher launcher = new Launcher() {
			@Override
			protected String getMainClass() throws Exception {
				super.createClassLoader(List.of());
				return null;
			}

			@Override
			protected List<Archive> getClassPathArchives() throws Exception {
				return super.getClassPathArchives();
			}
		};


		class SpringApplicationBuilderExtended extends SpringApplicationBuilder {
			@Override
			protected SpringApplication createSpringApplication(Class<?>... sources) {
				return super.createSpringApplication(sources);
			}
		}


		Archive archive = new Archive() {
			@Override
			public URL getUrl() throws MalformedURLException {
				return null;
			}

			@Override
			public Manifest getManifest() throws IOException {
				return null;
			}

			@Override
			public Iterator<Entry> iterator() {
				return null;
			}
		};
		archive.forEach(Archive.Entry::getName);
		Archive.EntryFilter entryFilter = entry -> false;
		archive.getNestedArchives(entryFilter, entryFilter);
		archive.iterator();
		archive.spliterator();

		DatabaseDriver.fromDataSource(null);
		MustacheProperties mustacheProperties = new MustacheProperties();
		mustacheProperties.getContentType();
		mustacheProperties.isAllowRequestOverride();
		mustacheProperties.isAllowSessionOverride();
		mustacheProperties.isCache();
		mustacheProperties.isExposeRequestAttributes();
		mustacheProperties.isExposeSessionAttributes();
		mustacheProperties.isExposeSpringMacroHelpers();
		mustacheProperties.setAllowRequestOverride(false);
		mustacheProperties.setAllowSessionOverride(false);
		mustacheProperties.setCache(false);
		mustacheProperties.setContentType(null);
		mustacheProperties.setExposeRequestAttributes(false);
		mustacheProperties.setExposeSessionAttributes(false);
		mustacheProperties.setExposeSpringMacroHelpers(false);

		WebMvcProperties webMvcProperties = new WebMvcProperties();
		webMvcProperties.getDateFormat();
		webMvcProperties.setDateFormat(null);
		class OnEndpointElementConditionExtended extends OnEndpointElementCondition {
			protected OnEndpointElementConditionExtended(String prefix, Class<? extends Annotation> annotationType) {
				super(prefix, annotationType);
			}

			@Override
			protected ConditionOutcome getDefaultEndpointsOutcome(ConditionContext context) {
				return super.getDefaultEndpointsOutcome(context);
			}
		}

		DynatraceProperties dynatraceProperties = new DynatraceProperties();
		dynatraceProperties.getDeviceId();
		dynatraceProperties.getGroup();
		dynatraceProperties.getTechnologyType();
		dynatraceProperties.setDeviceId(null);
		dynatraceProperties.setGroup(null);
		dynatraceProperties.setTechnologyType(null);

		class ErrorWebExceptionHandler extends AbstractErrorWebExceptionHandler {
			public ErrorWebExceptionHandler(ErrorAttributes errorAttributes, WebProperties.Resources resources, ApplicationContext applicationContext) {
				super(errorAttributes, resources, applicationContext);
			}

			@Override
			protected Map<String, Object> getErrorAttributes(org.springframework.web.reactive.function.server.ServerRequest request, boolean includeStackTrace) {
				return super.getErrorAttributes(request, includeStackTrace);
			}

			@Override
			protected org.springframework.web.reactive.function.server.RouterFunction<org.springframework.web.reactive.function.server.ServerResponse> getRoutingFunction(ErrorAttributes errorAttributes) {
				return null;
			}
		}
		ErrorWebExceptionHandler errorWebExceptionHandler = new ErrorWebExceptionHandler(null, null, null);
		errorWebExceptionHandler.getErrorAttributes(null, false);

		Saml2RelyingPartyProperties.Registration registration = new Saml2RelyingPartyProperties.Registration();
		registration.getIdentityprovider();
		OAuth2ResourceServerProperties.Jwt jwt = new OAuth2ResourceServerProperties.Jwt();
		jwt.getJwsAlgorithm();
		jwt.setJwsAlgorithm(null);

		ConfigData configData = new ConfigData(null);
		configData.getOptions();
		FlywayProperties flywayProperties = new FlywayProperties();
		flywayProperties.getOracleKerberosConfigFile();
		flywayProperties.isIgnoreFutureMigrations();
		flywayProperties.isIgnoreIgnoredMigrations();
		flywayProperties.isIgnoreMissingMigrations();
		flywayProperties.isIgnorePendingMigrations();
		flywayProperties.setIgnoreFutureMigrations(false);
		flywayProperties.setIgnoreIgnoredMigrations(false);
		flywayProperties.setIgnoreMissingMigrations(false);
		flywayProperties.setIgnorePendingMigrations(false);
		flywayProperties.setOracleKerberosConfigFile(null);

		GangliaProperties gangliaProperties = new GangliaProperties();
		gangliaProperties.getProtocolVersion();
		gangliaProperties.getRateUnits();
		gangliaProperties.setProtocolVersion(null);
		gangliaProperties.setRateUnits(null);

		ServerProperties.Undertow undertow1 = new ServerProperties.Undertow();
		ServerProperties.Undertow undertow = undertow1;
		undertow.isAllowEncodedSlash();

		WebMvcProperties.Contentnegotiation contentnegotiation = new WebMvcProperties.Contentnegotiation();
		contentnegotiation.isFavorPathExtension();
		contentnegotiation.setFavorPathExtension(false);
		WebMvcProperties.Pathmatch pathmatch = new WebMvcProperties.Pathmatch();
		pathmatch.isUseRegisteredSuffixPattern();
		pathmatch.isUseSuffixPattern();
		pathmatch.setUseRegisteredSuffixPattern(false);
		pathmatch.setUseSuffixPattern(false);

		KafkaProperties.Listener listener = new KafkaProperties.Listener();
		listener.isOnlyLogRecordMetadata();
		listener.setOnlyLogRecordMetadata(false);

		JarFileArchive entries = new JarFileArchive(null, null);
		entries.iterator();
		ExplodedArchive entries1 = new ExplodedArchive(null);
		entries1.iterator();
		JooqAutoConfiguration.DslContextConfiguration dslContextConfiguration = new JooqAutoConfiguration.DslContextConfiguration();
		dslContextConfiguration.jooqProvidersDefaultConfigurationCustomizer(null, null, null, null, null, null, null, null);

		class RabbitListenerConfigurer extends AbstractRabbitListenerContainerFactoryConfigurer {

			@Override
			public void configure(AbstractRabbitListenerContainerFactory factory, ConnectionFactory connectionFactory) {

			}

			protected RabbitListenerConfigurer() {
				super();
			}

			@Override
			protected void setRabbitProperties(RabbitProperties rabbitProperties) {
				super.setRabbitProperties(rabbitProperties);
			}
		}

		class Log4J2LoggingSystemExtended extends Log4J2LoggingSystem {
			public Log4J2LoggingSystemExtended(ClassLoader classLoader) {
				super(classLoader);
				loadConfiguration(null, null);
			}

		}

		SpringApplicationRunListener springApplicationRunListener = new SpringApplicationRunListener() {
			@Override
			public void started(ConfigurableApplicationContext context) {
				SpringApplicationRunListener.super.started(context);
			}

			@Override
			public void running(ConfigurableApplicationContext context) {
				SpringApplicationRunListener.super.running(context);
			}
		};
		springApplicationRunListener.started(null);
		springApplicationRunListener.running(null);

		EnvironmentEndpoint environmentEndpoint = new EnvironmentEndpoint(null);
		environmentEndpoint.sanitize(null, null);

		undertow1.isAllowEncodedSlash();
		undertow1.setAllowEncodedSlash(false);

		class RabbitTemplateConfigurerExtended extends RabbitTemplateConfigurer {
			@Override
			protected void setRabbitProperties(RabbitProperties rabbitProperties) {
				super.setRabbitProperties(rabbitProperties);
			}
		}

		class AppContextRunner extends AbstractApplicationContextRunner<ApplicationContextRunner, ConfigurableApplicationContext, AssertableApplicationContext> {

			protected AppContextRunner(Supplier<ConfigurableApplicationContext> contextFactory) {
				super(contextFactory);
			}

			protected AppContextRunner(Supplier<ConfigurableApplicationContext> contextFactory, boolean allowBeanDefinitionOverriding, List<ApplicationContextInitializer<? super ConfigurableApplicationContext>> applicationContextInitializers, TestPropertyValues environmentProperties, TestPropertyValues systemProperties, ClassLoader classLoader, ApplicationContext parent, List<BeanRegistration<?>> beanRegistrations, List<Configurations> configurations) {
				super(contextFactory, allowBeanDefinitionOverriding, applicationContextInitializers, environmentProperties, systemProperties, classLoader, parent, beanRegistrations, configurations);
			}

			@Override
			protected ApplicationContextRunner newInstance(Supplier<ConfigurableApplicationContext> contextFactory, boolean allowBeanDefinitionOverriding, List<ApplicationContextInitializer<? super ConfigurableApplicationContext>> applicationContextInitializers, TestPropertyValues environmentProperties, TestPropertyValues systemProperties, ClassLoader classLoader, ApplicationContext parent, List<BeanRegistration<?>> beanRegistrations, List<Configurations> configurations) {
				return null;
			}
		}
		AppContextRunner appContextRunner = new AppContextRunner(null);
		ApplicationContextRunner applicationContextRunner = appContextRunner.newInstance(null, false, null, null, null, null, null, null, null);

		class RabbitListenerContainerFactoryBla extends AbstractRabbitListenerContainerFactoryConfigurer {

			protected RabbitListenerContainerFactoryBla() {
				super();
			}

			@Override
			public void configure(AbstractRabbitListenerContainerFactory factory, ConnectionFactory connectionFactory) {

			}
		}

		ApplicationReadyEvent applicationReadyEvent = new ApplicationReadyEvent(null, null, null);
		ApplicationStartedEvent applicationStartedEvent = new ApplicationStartedEvent(null, null, null);
		DirectRabbitListenerContainerFactoryConfigurer directRabbitListenerContainerFactoryConfigurer = new DirectRabbitListenerContainerFactoryConfigurer();
		HealthEndpoint healthEndpoint = new HealthEndpoint(null, null);
		HealthEndpointWebExtension healthEndpointWebExtension = new HealthEndpointWebExtension(null, null);
		IncludeExcludeEndpointFilter discoveredEndpointIncludeExcludeEndpointFilter = new IncludeExcludeEndpointFilter<>(DiscoveredEndpoint.class, new StandardEnvironment(), "", IncludeExcludeEndpointFilter.DefaultIncludes.JMX);
		class PackagerExtended extends Packager {

			protected PackagerExtended(File source, LayoutFactory layoutFactory) {
				super(source, layoutFactory);
			}
		}

		RabbitTemplateConfigurer rabbitTemplateConfigurer = new RabbitTemplateConfigurer();
		ReactiveHealthEndpointWebExtension reactiveHealthEndpointWebExtension = new ReactiveHealthEndpointWebExtension(null, null);
		Repackager repackager = new Repackager(null, null);
		RestartClassLoader restartClassLoader = new RestartClassLoader(null, null, null, null);
		SimpleRabbitListenerContainerFactoryConfigurer simpleRabbitListenerContainerFactoryConfigurer = new SimpleRabbitListenerContainerFactoryConfigurer();

		class ApplicationContextAssertProviderImpl implements ApplicationContextAssertProvider<GenericApplicationContext> {

			@Override
			public ApplicationContextAssert<GenericApplicationContext> assertThat() {
				return null;
			}

			@Override
			public GenericApplicationContext getSourceApplicationContext() {
				return null;
			}

			@Override
			public <T extends GenericApplicationContext> T getSourceApplicationContext(Class<T> requiredType) {
				return null;
			}

			@Override
			public Throwable getStartupFailure() {
				return null;
			}

			@Override
			public void close() {

			}

			@Override
			public String getId() {
				return null;
			}

			@Override
			public String getApplicationName() {
				return null;
			}

			@Override
			public String getDisplayName() {
				return null;
			}

			@Override
			public long getStartupDate() {
				return 0;
			}

			@Override
			public ApplicationContext getParent() {
				return null;
			}

			@Override
			public AutowireCapableBeanFactory getAutowireCapableBeanFactory() throws IllegalStateException {
				return null;
			}

			@Override
			public BeanFactory getParentBeanFactory() {
				return null;
			}

			@Override
			public boolean containsLocalBean(String s) {
				return false;
			}

			@Override
			public boolean containsBeanDefinition(String s) {
				return false;
			}

			@Override
			public int getBeanDefinitionCount() {
				return 0;
			}

			@Override
			public String[] getBeanDefinitionNames() {
				return new String[0];
			}

			@Override
			public <T> ObjectProvider<T> getBeanProvider(Class<T> aClass, boolean b) {
				return null;
			}

			@Override
			public <T> ObjectProvider<T> getBeanProvider(ResolvableType resolvableType, boolean b) {
				return null;
			}

			@Override
			public String[] getBeanNamesForType(ResolvableType resolvableType) {
				return new String[0];
			}

			@Override
			public String[] getBeanNamesForType(ResolvableType resolvableType, boolean b, boolean b1) {
				return new String[0];
			}

			@Override
			public String[] getBeanNamesForType(Class<?> aClass) {
				return new String[0];
			}

			@Override
			public String[] getBeanNamesForType(Class<?> aClass, boolean b, boolean b1) {
				return new String[0];
			}

			@Override
			public <T> Map<String, T> getBeansOfType(Class<T> aClass) throws BeansException {
				return null;
			}

			@Override
			public <T> Map<String, T> getBeansOfType(Class<T> aClass, boolean b, boolean b1) throws BeansException {
				return null;
			}

			@Override
			public String[] getBeanNamesForAnnotation(Class<? extends Annotation> aClass) {
				return new String[0];
			}

			@Override
			public Map<String, Object> getBeansWithAnnotation(Class<? extends Annotation> aClass) throws BeansException {
				return null;
			}

			@Override
			public <A extends Annotation> A findAnnotationOnBean(String s, Class<A> aClass) throws NoSuchBeanDefinitionException {
				return null;
			}

			@Override
			public <A extends Annotation> A findAnnotationOnBean(String s, Class<A> aClass, boolean b) throws NoSuchBeanDefinitionException {
				return null;
			}

			@Override
			public Object getBean(String s) throws BeansException {
				return null;
			}

			@Override
			public <T> T getBean(String s, Class<T> aClass) throws BeansException {
				return null;
			}

			@Override
			public Object getBean(String s, Object... objects) throws BeansException {
				return null;
			}

			@Override
			public <T> T getBean(Class<T> aClass) throws BeansException {
				return null;
			}

			@Override
			public <T> T getBean(Class<T> aClass, Object... objects) throws BeansException {
				return null;
			}

			@Override
			public <T> ObjectProvider<T> getBeanProvider(Class<T> aClass) {
				return null;
			}

			@Override
			public <T> ObjectProvider<T> getBeanProvider(ResolvableType resolvableType) {
				return null;
			}

			@Override
			public boolean containsBean(String s) {
				return false;
			}

			@Override
			public boolean isSingleton(String s) throws NoSuchBeanDefinitionException {
				return false;
			}

			@Override
			public boolean isPrototype(String s) throws NoSuchBeanDefinitionException {
				return false;
			}

			@Override
			public boolean isTypeMatch(String s, ResolvableType resolvableType) throws NoSuchBeanDefinitionException {
				return false;
			}

			@Override
			public boolean isTypeMatch(String s, Class<?> aClass) throws NoSuchBeanDefinitionException {
				return false;
			}

			@Override
			public Class<?> getType(String s) throws NoSuchBeanDefinitionException {
				return null;
			}

			@Override
			public Class<?> getType(String s, boolean b) throws NoSuchBeanDefinitionException {
				return null;
			}

			@Override
			public String[] getAliases(String s) {
				return new String[0];
			}

			@Override
			public void publishEvent(Object event) {

			}

			@Override
			public String getMessage(String code, Object[] args, String defaultMessage, Locale locale) {
				return null;
			}

			@Override
			public String getMessage(String code, Object[] args, Locale locale) throws NoSuchMessageException {
				return null;
			}

			@Override
			public String getMessage(MessageSourceResolvable resolvable, Locale locale) throws NoSuchMessageException {
				return null;
			}

			@Override
			public Environment getEnvironment() {
				return null;
			}

			@Override
			public Resource[] getResources(String locationPattern) throws IOException {
				return new Resource[0];
			}

			@Override
			public Resource getResource(String location) {
				return null;
			}

			@Override
			public ClassLoader getClassLoader() {
				return null;
			}
		}
		ApplicationContextAssertProviderImpl applicationContextAssertProvider = new ApplicationContextAssertProviderImpl();
		applicationContextAssertProvider.assertThat();

		JsonContent<Object> jsonContent = new JsonContent<>(null, null, null);
		jsonContent.assertThat();

		DatabaseDriver driver = DatabaseDriver.GAE;

		DataSourceInitializationMode dataSourceInitializationMode = DataSourceInitializationMode.ALWAYS;
	}
}
