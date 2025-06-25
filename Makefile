SHELL := /bin/bash

release: bump_auto

release-patch:
	@$(MAKE) bump TYPE=patch

release-minor:
	@$(MAKE) bump TYPE=minor

release-major:
	@$(MAKE) bump TYPE=major

bump:
	@echo "Fetching remote tags..."
	@git fetch --tags
	@LAST_TAG=$$(git tag --sort=-v:refname | head -n 1); \
	if [ -z "$$LAST_TAG" ]; then LAST_TAG="v0.0.0"; fi; \
	echo "Last tag: $$LAST_TAG"; \
	VERSION=$$(echo $$LAST_TAG | sed 's/^v//'); \
	IFS='.' read -r MAJOR MINOR PATCH <<< "$$VERSION"; \
	case "$(TYPE)" in \
		major) MAJOR=$$((MAJOR+1)); MINOR=0; PATCH=0 ;; \
		minor) MINOR=$$((MINOR+1)); PATCH=0 ;; \
		patch) PATCH=$$((PATCH+1)) ;; \
	esac; \
	NEW_TAG="v$$MAJOR.$$MINOR.$$PATCH"; \
	echo "✅ New tag would be: $$NEW_TAG"; \
	if git rev-parse "$$NEW_TAG" >/dev/null 2>&1; then \
		echo "❌ Tag $$NEW_TAG already exists. Aborting."; \
		exit 1; \
	fi; \
	# Uncomment these lines to actually create and push the tag:
	#git tag "$$NEW_TAG"; \
	#git push origin "$$NEW_TAG"

bump_auto:
	@echo "Fetching remote tags..."
	@git fetch --tags
	@LAST_TAG=$$(git tag --sort=-v:refname | head -n 1); \
	if [ -z "$$LAST_TAG" ]; then LAST_TAG="v0.0.0"; fi; \
	echo "Last tag: $$LAST_TAG"; \
	VERSION=$$(echo $$LAST_TAG | sed 's/^v//'); \
	if [ -z "$$VERSION" ]; then VERSION="0.0.0"; fi; \
	echo "VERSION extracted: $$VERSION"; \
	IFS='.' read -r MAJOR MINOR PATCH <<< "$$VERSION"; \
	if [ -z "$$MAJOR" ] || [ -z "$$MINOR" ] || [ -z "$$PATCH" ]; then \
		echo "Erreur : Version incorrecte (MAJOR=$$MAJOR, MINOR=$$MINOR, PATCH=$$PATCH)"; \
		exit 1; \
	fi; \
	echo "MAJOR=$$MAJOR, MINOR=$$MINOR, PATCH=$$PATCH"; \
	# Analyse des commits pour déterminer le type de version \
	TYPE=$$( \
		if git log "$$LAST_TAG"..HEAD --oneline | grep -qE "(BREAKING CHANGE|#major)"; then \
			echo "major"; \
		elif git log "$$LAST_TAG"..HEAD --oneline | grep -qE "(feat|#minor)"; then \
			echo "minor"; \
		else \
			echo "patch"; \
		fi \
	); \
	echo "Detected update type: $$TYPE"; \
	case "$$TYPE" in \
		major) MAJOR=$$((MAJOR+1)); MINOR=0; PATCH=0 ;; \
		minor) MINOR=$$((MINOR+1)); PATCH=0 ;; \
		patch) PATCH=$$((PATCH+1)) ;; \
	esac; \
	NEW_TAG="v$$MAJOR.$$MINOR.$$PATCH"; \
	echo "✅ New tag would be: $$NEW_TAG"; \
	if git rev-parse "$$NEW_TAG" >/dev/null 2>&1; then \
		echo "❌ Tag $$NEW_TAG already exists. Aborting."; \
		exit 1; \
	fi; \
	# Uncomment these lines to actually create and push the tag:
	#git tag "$$NEW_TAG"; \
	#git push origin "$$NEW_TAG"