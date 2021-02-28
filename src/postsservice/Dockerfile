#See https://aka.ms/containerfastmode to understand how Visual Studio uses this Dockerfile to build your images for faster debugging.

FROM mcr.microsoft.com/dotnet/aspnet:5.0-buster-slim AS base
WORKDIR /app
ENV ASPNETCORE_URLS=http://+:5000
EXPOSE 5000

FROM mcr.microsoft.com/dotnet/sdk:5.0-buster-slim AS build
WORKDIR /src
COPY PostsService.csproj ./
RUN dotnet restore "./PostsService.csproj"
COPY . .
WORKDIR "/src/."
RUN dotnet build "PostsService.csproj" -c Release -o /app/build

FROM build AS publish
RUN dotnet publish "PostsService.csproj" -c Release -o /app/publish

FROM base AS final
WORKDIR /app
COPY --from=publish /app/publish .
ENTRYPOINT ["dotnet", "PostsService.dll"]
