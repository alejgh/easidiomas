#See https://aka.ms/containerfastmode to understand how Visual Studio uses this Dockerfile to build your images for faster debugging.

FROM mcr.microsoft.com/dotnet/aspnet:5.0-buster-slim AS base
WORKDIR /app
ENV ASPNETCORE_URLS=http://+:5000
EXPOSE 5000

FROM mcr.microsoft.com/dotnet/sdk:5.0-buster-slim AS build
WORKDIR /src
COPY ["languageidentificationservice.csproj", ""]
RUN dotnet restore "./languageidentificationservice.csproj"
COPY . .
WORKDIR "/src/."
RUN dotnet build "languageidentificationservice.csproj" -c Release -o /app/build

FROM build AS publish
RUN dotnet publish "languageidentificationservice.csproj" -c Release -o /app/publish

FROM base AS final
WORKDIR /app
COPY --from=publish /app/publish .
ENV GOOGLE_APPLICATION_CREDENTIALS="easidiomas-24e1ab5e59a1.json"
ENTRYPOINT ["dotnet", "languageidentificationservice.dll"]