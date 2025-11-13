package com.pnfsoftware.jeb.core.units.code.android.render;

public class DexDisassemblyProperties {
   Boolean showAddresses;
   Boolean showBytecode;
   Boolean showAnnotations;
   Boolean showDebugDirectives;
   Boolean showLineNumbers;
   Boolean usePForParameters;
   Boolean smaliCompatibility;
   Integer classSeparatorLength;
   Integer methodSeparatorLength;
   Boolean showSpaceBetweenBlocks;
   Boolean showInstructionsInGaps;
   Boolean generateCatchDirectivesAtMethodEnd;
   Boolean showActualCallsites;
   Boolean showOriginalNames;

   public Boolean getShowAddresses() {
      return this.showAddresses;
   }

   public void setShowAddresses(Boolean var1) {
      this.showAddresses = var1;
   }

   public Boolean getShowBytecode() {
      return this.showBytecode;
   }

   public void setShowBytecode(Boolean var1) {
      this.showBytecode = var1;
   }

   public Boolean getShowAnnotations() {
      return this.showAnnotations;
   }

   public void setShowAnnotations(Boolean var1) {
      this.showAnnotations = var1;
   }

   public Boolean getShowDebugDirectives() {
      return this.showDebugDirectives;
   }

   public void setShowDebugDirectives(Boolean var1) {
      this.showDebugDirectives = var1;
   }

   public Boolean getShowLineNumbers() {
      return this.showLineNumbers;
   }

   public void setShowLineNumbers(Boolean var1) {
      this.showLineNumbers = var1;
   }

   public Boolean getUsePForParameters() {
      return this.usePForParameters;
   }

   public void setUsePForParameters(Boolean var1) {
      this.usePForParameters = var1;
   }

   public Boolean getSmaliCompatibility() {
      return this.smaliCompatibility;
   }

   public void setSmaliCompatibility(Boolean var1) {
      this.smaliCompatibility = var1;
   }

   public Integer getClassSeparatorLength() {
      return this.classSeparatorLength;
   }

   public void setClassSeparatorLength(Integer var1) {
      this.classSeparatorLength = var1;
   }

   public Integer getMethodSeparatorLength() {
      return this.methodSeparatorLength;
   }

   public void setMethodSeparatorLength(Integer var1) {
      this.methodSeparatorLength = var1;
   }

   public Boolean getShowSpaceBetweenBlocks() {
      return this.showSpaceBetweenBlocks;
   }

   public void setShowSpaceBetweenBlocks(Boolean var1) {
      this.showSpaceBetweenBlocks = var1;
   }

   public void setShowInstructionsInGaps(Boolean var1) {
      this.showInstructionsInGaps = var1;
   }

   public Boolean getShowInstructionsInGaps() {
      return this.showInstructionsInGaps;
   }

   public void setGenerateCatchDirectivesAtMethodEnd(Boolean var1) {
      this.generateCatchDirectivesAtMethodEnd = var1;
   }

   public Boolean getGenerateCatchDirectivesAtMethodEnd() {
      return this.generateCatchDirectivesAtMethodEnd;
   }

   public void setShowActualCallsites(Boolean var1) {
      this.showActualCallsites = var1;
   }

   public Boolean getShowActualCallsites() {
      return this.showActualCallsites;
   }

   public void setShowOriginalNames(Boolean var1) {
      this.showOriginalNames = var1;
   }

   public Boolean getShowOriginalNames() {
      return this.showOriginalNames;
   }
}
