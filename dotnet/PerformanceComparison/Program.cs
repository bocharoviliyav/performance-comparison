var builder = WebApplication.CreateSlimBuilder(args)
    .AddLogging()
    .AddDatabase()
    .AddServices();

var app = builder.Build();

app.MapGet("/api/v1/person/{id:long}", async (long id, IPersonService personService, CancellationToken cancellationToken) => 
    await personService.GetByIdAsync(id, cancellationToken)
        is { } value
        ? Results.Ok(value)
        : Results.NotFound());
app.MapPost("/api/v1/person", async (PersonEntity person, IPersonService personService, CancellationToken cancellationToken) => 
    Results.Ok(await personService.CreateOrUpdateAsync(person, cancellationToken)));

app.Run();