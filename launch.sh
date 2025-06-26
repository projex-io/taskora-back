#!/bin/bash

set -e

CLEAN=false
SKIP_BUILD=false

print_help() {
  echo ""
  echo "🚀 Script de lancement Docker Compose"
  echo ""
  echo "Utilisation : ./launch.sh [options]"
  echo ""
  echo "Options :"
  echo "  -c, --clean     Supprime aussi les volumes Docker (reset complet)"
  echo "  -s, --skip      Skip l'étape de build Gradle"
  echo "  -h, --help      Affiche cette aide"
  echo ""
  echo "Par défaut, les volumes sont conservés et le build est exécuté."
}

# Analyse des options (supporte plusieurs options)
while [[ $# -gt 0 ]]; do
  case "$1" in
    -c|--clean)
      CLEAN=true
      shift
      ;;
    -s|--skip)
      SKIP_BUILD=true
      shift
      ;;
    -h|--help)
      print_help
      exit 0
      ;;
    *)
      echo "❌ Option inconnue : $1"
      print_help
      exit 1
      ;;
  esac
done

# Stop et (éventuellement) clean
echo "🛑 Arrêt des conteneurs..."
if [ "$CLEAN" = true ]; then
  echo "🗑 Suppression des volumes activée (--clean)"
  docker compose down --volumes --remove-orphans > /dev/null 2>&1
else
  docker compose down --remove-orphans > /dev/null 2>&1
fi

# Build Gradle (sauf si skip)
if [ "$SKIP_BUILD" = false ]; then
  echo "🔨 Build Gradle en cours (clean build sans tests)..."
  ./gradlew clean build > /dev/null 2>&1
else
  echo "⏭ Bypass Gradle Build (--skip)"
fi

# Rebuild
echo "🛠 Rebuild des images sans cache..."
docker compose build --no-cache > /dev/null 2>&1

# Lancement
echo "🚀 Lancement des services en mode détaché..."
docker compose up -d

echo -n "✅ Application lancée"
[ "$CLEAN" = true ] && echo " (volumes supprimés)" || echo ""
[ "$SKIP_BUILD" = true ] && echo " (build Gradle sauté)"
