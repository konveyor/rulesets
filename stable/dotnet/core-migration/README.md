# .NET Core Migration Ruleset

This ruleset helps identify and guide the migration of ASP.NET MVC applications from .NET Framework to ASP.NET Core / .NET Core.

## Overview

The ruleset is designed to be used with the Konveyor analyzer and the C# analyzer provider to detect incompatible APIs and patterns in .NET Framework applications that need to be updated for .NET Core compatibility.

## Ruleset Structure

The rules are organized into separate files for clarity and maintainability:

### 01-web-framework-migration.yaml
Covers migration of core web framework components:
- System.Web namespace removal
- HttpApplication and Global.asax replacement
- MVC Controller updates
- Routing changes
- Web API migration
- Bundling and optimization
- Area registration
- HttpContext.Current migration
- Server.MapPath replacement
- Web Forms (System.Web.UI) migration
- HttpRuntime replacement

**Effort Range**: 2-10 story points per issue
**Rule Count**: 11 rules

### 02-authentication-security-migration.yaml
Covers authentication and security component migration:
- WebSecurity to ASP.NET Core Identity
- OAuth and external authentication providers
- Membership API replacement
- Forms authentication migration
- DotNetOpenAuth replacement
- Transaction handling
- Anti-forgery token updates
- MachineKey to Data Protection API

**Effort Range**: 1-8 story points per issue
**Rule Count**: 8 rules

### 03-entity-framework-migration.yaml
Covers data access migration:
- Entity Framework 6 to EF Core
- DbContext updates
- Database initialization
- DbSet API changes
- Spatial data types
- Lazy loading configuration
- Connection string handling

**Effort Range**: 3-7 story points per issue
**Rule Count**: 6 rules

### 04-configuration-migration.yaml
Covers configuration and state management:
- Web.config to appsettings.json
- Configuration API changes
- Connection string management
- Application state replacement
- Session state updates
- Global configuration removal
- Bundling configuration
- System.Configuration.Install removal

**Effort Range**: 3-7 story points per issue
**Rule Count**: 10 rules

### 05-runtime-utilities-migration.yaml
Covers runtime and utility class changes:
- Thread.Abort removal
- BinaryFormatter obsolescence
- AppDomain limitations (general)
- AppDomain.CreateDomain (specific)
- AppDomain.Unload (specific)
- .NET Remoting removal
- Assembly reflection changes
- Code Access Security removal
- View rendering patterns
- Model binding updates
- Delegate.BeginInvoke/EndInvoke async pattern
- WCF (System.ServiceModel) migration

**Effort Range**: 3-10 story points per issue
**Rule Count**: 13 rules

### 06-advanced-runtime-migration.yaml
Covers advanced enterprise and runtime features:
- System.EnterpriseServices (COM+) removal
- Workflow Foundation migration to CoreWF
- XSLT script blocks not supported
- AssemblyBuilder.Save limitations (.NET 5-8)
- Security Transparency attributes removal
- Multi-module assemblies not supported
- System.Drawing (cross-platform considerations)
- System.Windows.Forms (cross-platform alternatives)
- AssemblyBuilderAccess enum restrictions

**Effort Range**: 3-10 story points per issue
**Rule Count**: 9 rules

## Usage with Konveyor

### Prerequisites
1. Install Konveyor CLI (kantra)
2. Build and run the C# analyzer provider
3. Ensure the provider is accessible (gRPC or Unix socket)

### Running the Analysis

```bash
# Start the C# analyzer provider
cargo run -- --port 9000 --name c-sharp --db-path migration-analysis.db

# Run Konveyor analysis (in a separate terminal)
kantra analyze \
  --input /path/to/nerd-dinner \
  --output ./analysis-output \
  --rules ./rulesets/dotnet-core-migration \
  --target dotnet-core \
  --source dotnet
```

### Example Provider Configuration

When initializing the provider for the NerdDinner application:

```json
{
  "location": "/path/to/testdata/nerd-dinner/mvc4/NerdDinner",
  "analysisMode": "source-only",
  "providerSpecificConfig": {
    "ilspy_cmd": "${HOME}/.dotnet/tools/ilspycmd",
    "paket_cmd": "${HOME}/.dotnet/tools/paket"
  }
}
```

## Expected Results

When analyzing the NerdDinner application, you should see incidents reported for:

- **System.Web dependencies** (Controllers, Global.asax, HttpApplication)
- **WebSecurity and OAuth** (AccountController authentication code)
- **Entity Framework** (DbContext usage, connection strings)
- **Configuration** (WebConfigurationManager in AuthConfig)
- **Model Binders** (DbGeographyModelBinder)

## Effort Estimation

The ruleset provides effort estimates to help with migration planning:

| Category | Total Effort Range |
|----------|-------------------|
| Web Framework | 39-53 story points |
| Authentication & Security | 35-50 story points |
| Entity Framework | 18-28 story points |
| Configuration | 32-50 story points |
| Runtime & Utilities | 52-75 story points |
| Advanced Runtime Features | 36-78 story points |
| **Total** | **212-334 story points** |

*Note: Actual effort will depend on the size and complexity of your application.*

## Migration Priority

Recommended migration order:

1. **Configuration** - Set up new configuration system first
2. **Web Framework** - Migrate to ASP.NET Core basics (Startup, controllers)
3. **Entity Framework** - Update data access layer
4. **Authentication** - Implement ASP.NET Core Identity
5. **Runtime & Utilities** - Address remaining compatibility issues
6. **Advanced Runtime Features** - Handle enterprise services, workflow, and specialized runtime features

## Additional Resources

- [Microsoft .NET Core Migration Guide](https://learn.microsoft.com/en-us/aspnet/core/migration/proper-to-2x)
- [ASP.NET to ASP.NET Core Migration](https://learn.microsoft.com/en-us/aspnet/core/migration/mvc)
- [.NET Upgrade Assistant](https://dotnet.microsoft.com/platform/upgrade-assistant)
- [EF6 to EF Core Porting](https://learn.microsoft.com/en-us/ef/efcore-and-ef6/porting/)

## Testing the Ruleset

To test these rules against the NerdDinner sample application:

```bash
# Run the demo
make run-demo

# Or run integration tests
make run-integration-tests
```

## Contributing

To add new rules:

1. Choose the appropriate category file (or create a new one)
2. Follow the existing rule format
3. Test against sample code
4. Update this README with new rule counts and effort estimates

## Rule Format

Each rule follows this structure:

```yaml
- category: mandatory|optional
  customVariables: []
  description: Brief description of the issue
  effort: 1-10 (story points)
  labels:
  - konveyor.io/source=dotnet
  - konveyor.io/target=dotnet-core
  links:
  - title: Documentation title
    url: https://docs.microsoft.com/...
  message: |
    Detailed explanation of the issue and migration actions.
  ruleID: unique-rule-identifier
  when:
    csharp.referenced:
      location: ALL|METHOD|CLASS|FIELD
      pattern: RegexPattern
```

## License

This ruleset is provided as-is for use with the Konveyor project.
