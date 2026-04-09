# Project name
JAR_NAME = GameOfLife.jar

# Source directories
SRC_DIRS = Logic Rules Interface

# Output directory
BUILD_DIR = build

# Library directory
LIB_DIR = lib

# JTattoo JAR
JTATTOO_JAR = $(LIB_DIR)/jtattoo.jar
JTATTOO_URL = https://jtattoo.de/downloads/JTattoo-1.6.13.jar
 
# Classpath
CLASSPATH = $(JTATTOO_JAR):$(BUILD_DIR)

# Java source files
JAVA_FILES = $(shell find $(SRC_DIRS) -name "*.java")

# Class files
CLASS_FILES = $(JAVA_FILES:%.java=$(BUILD_DIR)/%.class)

# Main class
MAIN_CLASS = Game

# Java compiler flags
JAVAC_FLAGS = -source 11 -target 11

# Manifest file
MANIFEST = $(BUILD_DIR)/MANIFEST.MF

all: compile run

# Download JTattoo if not exists
$(JTATTOO_JAR):
	@mkdir -p $(LIB_DIR)
	@echo "Downloading JTattoo..."
	@wget -O $(JTATTOO_JAR) $(JTATTOO_URL)


$(CLASS_FILES): $(JTATTOO_JAR)
	@mkdir -p $(dir $@)
	@javac $(JAVAC_FLAGS) -d $(BUILD_DIR) -cp "$(CLASSPATH)" ${JAVA_FILES} 

# Default target
$(JAR_NAME): $(MANIFEST) $(CLASS_FILES) $(JTATTOO_JAR)
	@jar -cvfm $(JAR_NAME) $(MANIFEST) -C $(BUILD_DIR) . -C . images

# Create manifest
$(MANIFEST):
	@mkdir -p $(dir $@)
	@echo "Main-Class: Interface.Main" > $@
	@echo "Class-Path: lib/jtattoo.jar" >> $@
	@echo "Created-By: KJ" >> $@
	@echo "Build-Number: $(shell git rev-list --count HEAD 2>/dev/null || echo 'unknown')" >> $@
	@echo "Version: $(shell git describe --tags --always 2>/dev/null || echo 'unknown')" >> $@
	@echo "Build-Date: $(shell date '+%Y-%m-%d %H:%M:%S')" >> $@
	@echo "Build-User: $(shell whoami)" >> $@

# Run the application
run: $(JAR_NAME)
	java -jar $(JAR_NAME)

# Clean build
clean:
	rm -rf $(BUILD_DIR) $(LIB_DIR) $(JAR_NAME)

# Format Java files
format:
	find $(SRC_DIRS) -name "*.java" -exec google-java-format -i {} \;

compile: $(JAR_NAME)

.PHONY: compile clean run format

