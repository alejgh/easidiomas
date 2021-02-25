FROM mcr.microsoft.com/dotnet/aspnet:5.0-buster-slim AS base
WORKDIR /app
EXPOSE 80

FROM mcr.microsoft.com/dotnet/sdk:5.0-buster-slim AS build
WORKDIR /src
COPY translationsservice.csproj ./
COPY easidiomas-24e1ab5e59a1.json ./
RUN dotnet restore "./translationsservice.csproj"
COPY . .
WORKDIR "/src/."
RUN dotnet build "translationsservice.csproj" -c Release -o /app/build

FROM build AS publish
RUN dotnet publish "translationsservice.csproj" -c Release -o /app/publish

FROM base AS final
WORKDIR /app
COPY --from=publish /app/publish .
ENV GOOGLE_APPLICATION_CREDENTIALS="easidiomas-24e1ab5e59a1.json"
ENTRYPOINT ["dotnet", "translationsservice.dll"]