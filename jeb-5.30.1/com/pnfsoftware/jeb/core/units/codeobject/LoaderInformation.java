package com.pnfsoftware.jeb.core.units.codeobject;

import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.io.Endianness;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;

@Ser
public class LoaderInformation implements ILoaderInformation {
   @SerId(1)
   int flags;
   @SerId(2)
   String version;
   @SerId(3)
   ProcessorType processorType;
   @SerId(4)
   OperatingSystemType osType;
   @SerId(5)
   SubsystemType ssType;
   @SerId(6)
   boolean libraryFile;
   @SerId(7)
   boolean littleEndian;
   @SerId(8)
   int wordSize;
   @SerId(9)
   long compilationTimestamp;
   @SerId(10)
   long imageBase;
   @SerId(11)
   long imageSize;
   @SerId(12)
   long entryPoint;
   @SerId(13)
   long overlayOffset;
   @SerId(14)
   String notes;

   @Override
   public int getFlags() {
      return this.flags;
   }

   @Override
   public String getVersion() {
      return this.version;
   }

   @Override
   public ProcessorType getTargetProcessor() {
      return this.processorType;
   }

   @Override
   public SubsystemType getTargetSubsystem() {
      return this.ssType;
   }

   @Override
   public Endianness getEndianness() {
      return this.littleEndian ? Endianness.LITTLE_ENDIAN : Endianness.BIG_ENDIAN;
   }

   @Override
   public int getWordSize() {
      return this.wordSize;
   }

   @Override
   public long getCompilationTimestamp() {
      return this.compilationTimestamp;
   }

   @Override
   public long getImageBase() {
      return this.imageBase;
   }

   @Override
   public long getImageSize() {
      return this.imageSize;
   }

   @Override
   public long getEntryPoint() {
      return this.entryPoint;
   }

   @Override
   public long getOverlayOffset() {
      return this.overlayOffset;
   }

   @Override
   public String getNotes() {
      return this.notes;
   }

   @Override
   public String toString() {
      return Strings.ff("base=%X,size=%X", this.imageBase, this.imageSize);
   }

   public static class Builder {
      private int flags;
      private String version;
      private ProcessorType processorType;
      private SubsystemType ssType;
      private boolean littleEndian;
      private int wordSize;
      private long compilationTimestamp;
      private long imageBase;
      private long imageSize;
      private long entryPoint;
      private long overlayOffset;
      private String notes;

      public LoaderInformation.Builder setFlags(int var1) {
         this.flags = var1;
         return this;
      }

      public LoaderInformation.Builder setVersion(String var1) {
         this.version = var1;
         return this;
      }

      public LoaderInformation.Builder setTargetProcessor(ProcessorType var1) {
         this.processorType = var1;
         return this;
      }

      public LoaderInformation.Builder setTargetSubsystem(SubsystemType var1) {
         this.ssType = var1;
         return this;
      }

      public LoaderInformation.Builder setEndianness(Endianness var1) {
         this.littleEndian = var1.isLittle();
         return this;
      }

      public LoaderInformation.Builder setWordSize(int var1) {
         this.wordSize = var1;
         return this;
      }

      public LoaderInformation.Builder setCompilationTimestamp(long var1) {
         this.compilationTimestamp = var1;
         return this;
      }

      public LoaderInformation.Builder setImageBase(long var1) {
         this.imageBase = var1;
         return this;
      }

      public LoaderInformation.Builder setImageSize(long var1) {
         this.imageSize = var1;
         return this;
      }

      public LoaderInformation.Builder setEntryPoint(long var1) {
         this.entryPoint = var1;
         return this;
      }

      public LoaderInformation.Builder setOverlayOffset(long var1) {
         this.overlayOffset = var1;
         return this;
      }

      public LoaderInformation.Builder setNotes(String var1) {
         this.notes = var1;
         return this;
      }

      public LoaderInformation build() {
         LoaderInformation var1 = new LoaderInformation();
         var1.flags = this.flags;
         var1.version = this.version;
         var1.processorType = this.processorType;
         var1.ssType = this.ssType;
         var1.littleEndian = this.littleEndian;
         var1.wordSize = this.wordSize;
         var1.compilationTimestamp = this.compilationTimestamp;
         var1.imageBase = this.imageBase;
         var1.imageSize = this.imageSize;
         var1.entryPoint = this.entryPoint;
         var1.overlayOffset = this.overlayOffset;
         var1.notes = this.notes;
         return var1;
      }
   }
}
