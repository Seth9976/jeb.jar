package com.pnfsoftware.jeb.core.units.code.asm.sig;

import com.pnfsoftware.jeb.core.units.code.asm.analyzer.ICompiler;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.LibraryID;
import com.pnfsoftware.jeb.core.units.codeobject.ProcessorType;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;

@Ser
public class NativeSignaturePackageMetadata {
   @SerId(1)
   ProcessorType targetProcessorType;
   @SerId(2)
   String name;
   @SerId(3)
   int version;
   @SerId(4)
   String description;
   @SerId(5)
   String author;
   @SerId(6)
   long creationTimestamp;
   @SerId(7)
   ICompiler targetCompiler;
   @SerId(8)
   LibraryID libraryId;
   @SerId(9)
   int uuid;

   public static NativeSignaturePackageMetadata create(
      ProcessorType var0, String var1, int var2, int var3, String var4, String var5, ICompiler var6, LibraryID var7
   ) {
      NativeSignaturePackageMetadata var8 = new NativeSignaturePackageMetadata(var0, var1);
      var8.version = var3;
      var8.description = var4;
      var8.author = var5;
      var8.creationTimestamp = System.currentTimeMillis() / 1000L;
      var8.targetCompiler = var6;
      var8.libraryId = var7;
      var8.uuid = var2;
      return var8;
   }

   private NativeSignaturePackageMetadata(ProcessorType var1, String var2) {
      if (var1 != null && var2 != null) {
         this.targetProcessorType = var1;
         this.name = var2;
      } else {
         throw new NullPointerException();
      }
   }

   public ProcessorType getTargetProcessorType() {
      return this.targetProcessorType;
   }

   public String getName() {
      return this.name;
   }

   public int getVersion() {
      return this.version;
   }

   public String getDescription() {
      return this.description;
   }

   public String getAuthor() {
      return this.author;
   }

   public long getCreationTimestamp() {
      return this.creationTimestamp;
   }

   public ICompiler getTargetCompiler() {
      return this.targetCompiler;
   }

   @Override
   public String toString() {
      return Strings.ff("%s/%s/%d/%s/%s/%d", this.targetProcessorType, this.name, this.version, this.description, this.author, this.creationTimestamp);
   }

   public LibraryID getLibraryId() {
      return this.libraryId;
   }

   public int getUuid() {
      return this.uuid;
   }
}
