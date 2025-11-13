package com.pnfsoftware.jeb.core.units.codeobject;

import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;

@Ser
public class SegmentInformation implements ISegmentInformation {
   @SerId(1)
   String name;
   @SerId(2)
   long offsetInFile;
   @SerId(3)
   long sizeInFile;
   @SerId(4)
   long offsetInMemory;
   @SerId(5)
   long sizeInMemory;
   @SerId(6)
   int flags;
   @SerId(7)
   long alignment;

   public SegmentInformation(String var1, long var2, long var4, long var6, long var8, int var10) {
      this.name = var1;
      this.offsetInFile = var2;
      this.sizeInFile = var4;
      this.offsetInMemory = var6;
      this.sizeInMemory = var8;
      this.flags = var10;
   }

   public SegmentInformation(SegmentInformation var1) {
      this.name = var1.name;
      this.offsetInFile = var1.offsetInFile;
      this.sizeInFile = var1.sizeInFile;
      this.offsetInMemory = var1.offsetInMemory;
      this.sizeInMemory = var1.sizeInMemory;
      this.flags = var1.flags;
   }

   @Override
   public String getName() {
      return this.name;
   }

   public void setName(String var1) {
      this.name = var1;
   }

   @Override
   public long getOffsetInFile() {
      return this.offsetInFile;
   }

   public void setOffsetInFile(long var1) {
      this.offsetInFile = var1;
   }

   @Override
   public long getSizeInFile() {
      return this.sizeInFile;
   }

   public void setSizeInFile(long var1) {
      this.sizeInFile = var1;
   }

   @Override
   public long getOffsetInMemory() {
      return this.offsetInMemory;
   }

   public void setOffsetInMemory(long var1) {
      this.offsetInMemory = var1;
   }

   @Override
   public long getSizeInMemory() {
      return this.sizeInMemory;
   }

   public void setSizeInMemory(long var1) {
      this.sizeInMemory = var1;
   }

   @Override
   public int getFlags() {
      return this.flags;
   }

   public void setFlags(int var1) {
      this.flags = var1;
   }

   @Override
   public long getAlignment() {
      return this.alignment;
   }

   public void setAlignment(long var1) {
      this.alignment = var1;
   }

   @Override
   public String toString() {
      return Strings.ff(
         "%s|%X|o=%X,s=%X|rva=%X,ms=%X,a=%X",
         this.getName(),
         this.getFlags(),
         this.getOffsetInFile(),
         this.getSizeInFile(),
         this.getOffsetInMemory(),
         this.getSizeInMemory(),
         this.getAlignment()
      );
   }
}
