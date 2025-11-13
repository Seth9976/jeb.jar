package com.pnfsoftware.jeb.corei.parsers.ihex;

import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.core.IUnitCreator;
import com.pnfsoftware.jeb.core.input.BytesInput;
import com.pnfsoftware.jeb.core.input.IInput;
import com.pnfsoftware.jeb.core.properties.IPropertyDefinitionManager;
import com.pnfsoftware.jeb.core.units.IUnit;
import com.pnfsoftware.jeb.core.units.IUnitProcessor;
import com.pnfsoftware.jeb.core.units.NotificationType;
import com.pnfsoftware.jeb.core.units.UnitNotification;
import com.pnfsoftware.jeb.core.units.code.asm.memory.IVirtualMemory;
import com.pnfsoftware.jeb.core.units.codeobject.AbstractCodeObjectUnit;
import com.pnfsoftware.jeb.core.units.codeobject.ILinkInfoProvider;
import com.pnfsoftware.jeb.core.units.codeobject.ISegmentInformation;
import com.pnfsoftware.jeb.core.units.codeobject.LoaderInformation;
import com.pnfsoftware.jeb.core.units.codeobject.ProcessorType;
import com.pnfsoftware.jeb.core.units.codeobject.ProcessorUtil;
import com.pnfsoftware.jeb.core.units.codeobject.SegmentInformation;
import com.pnfsoftware.jeb.util.collect.BytePipe;
import com.pnfsoftware.jeb.util.encoding.Conversion;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.io.EndianUtil;
import com.pnfsoftware.jeb.util.io.Endianness;
import com.pnfsoftware.jeb.util.io.IO;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

@Ser
public class eo extends AbstractCodeObjectUnit {
   private static final ILogger Uv = GlobalLog.getLogger(eo.class);
   @SerId(1)
   ProcessorType q;
   @SerId(2)
   byte[] RF;
   @SerId(3)
   int xK;
   @SerId(4)
   Endianness Dw;

   public eo(String var1, IInput var2, IUnitProcessor var3, IUnitCreator var4, IPropertyDefinitionManager var5) {
      super(var2, "ihex", var1, var3, var4, var5);
   }

   public ProcessorType q() {
      return this.q;
   }

   public int RF() {
      return this.xK;
   }

   @Override
   protected boolean processInternal() {
      String var1 = this.getPropertyManager().getString("Processor");
      this.q = ProcessorUtil.fromArchName(var1);
      this.Dw = this.getPropertyManager().getBoolean("BigEndian") ? Endianness.BIG_ENDIAN : Endianness.LITTLE_ENDIAN;
      this.xK = Conversion.stringToInt(this.getPropertyManager().getString("WantedWordsize"));
      long var2 = Conversion.stringToLong(this.getPropertyManager().getString("WantedImageBase"), -1L);
      if (this.q != null && this.q.isIntel()) {
         this.Dw = Endianness.LITTLE_ENDIAN;
      }

      try {
         try (InputStream var5 = this.getInput().getStream()) {
            byte[] var4 = IO.readInputStream(var5);
            ByteBuffer var6 = ByteBuffer.wrap(var4);
            nI var7 = new nI(var6, true, true);
            BytePipe var8 = new BytePipe();
            long var9 = 0L;
            boolean var11 = true;
            Long var12 = null;
            boolean var13 = false;

            while (var7.q()) {
               CU var14 = var7.RF();
               if (var13) {
                  this.addNotification(new UnitNotification(NotificationType.CORRUPTION, S.L("EOF record found, but more data follows")));
                  break;
               }

               switch (var14.RF()) {
                  case 0:
                     if (var11) {
                        this.q(var8.available());
                        var11 = false;
                        String var28 = Strings.ff("seg%03d", this.getSegmentCount());
                        this.addSegment(new SegmentInformation(var28, var8.available(), 0L, var9, 0L, 0));
                     }

                     var8.append(var14.xK());
                     break;
                  case 1:
                     this.q(var8.available());
                     var11 = false;
                     var13 = true;
                     break;
                  case 2:
                     var11 = true;
                     var9 = EndianUtil.bigEndianBytesToShort(var14.xK());
                     var9 <<= 4;
                     break;
                  case 3:
                     if (var12 != null) {
                        this.addNotification(
                           new UnitNotification(
                              NotificationType.WARNING, Strings.ff(S.L("A start address was previously specified and was overwritten: %Xh"), var12)
                           )
                        );
                     }

                     long var15 = EndianUtil.bigEndianBytesToShort(var14.xK()) & 65535L;
                     long var17 = EndianUtil.bigEndianBytesToShort(var14.xK(), 2) & 65535L;
                     var12 = var15 * 16L + var17;
                     break;
                  case 4:
                     var11 = true;
                     var9 = EndianUtil.bigEndianBytesToShort(var14.xK());
                     var9 <<= 16;
                     break;
                  case 5:
                     if (var12 != null) {
                        this.addNotification(
                           new UnitNotification(
                              NotificationType.WARNING, Strings.ff(S.L("A start address was previously specified and was overwritten: %Xh"), var12)
                           )
                        );
                     }

                     var12 = EndianUtil.bigEndianBytesToInt(var14.xK()) & 4294967295L;
                     break;
                  default:
                     this.addNotification(new UnitNotification(NotificationType.UNSUPPORTED_FEATURE, Strings.ff(S.L("Unknown record type: %d"), var14.RF())));
               }
            }

            this.RF = var8.getAll();
            if (this.xK == 0) {
               int var26 = var7.q(0);
               int var29 = var7.q(1);
               int var16 = var7.q(2);
               int var32 = var7.q(3);
               int var18 = var7.q(4);
               int var19 = var7.q(5);
               if (var26 > 0 && var29 > 0) {
                  if (var16 == 0 && var32 == 0 && var18 == 0 && var19 == 0) {
                     this.xK = 8;
                  } else if (var16 > 0 && var32 >= 0 && var18 == 0 && var19 == 0) {
                     this.xK = 16;
                  } else if (var16 == 0 && var32 == 0 && var18 > 0 && var19 >= 0) {
                     this.xK = 32;
                  }
               }
            }

            byte var27 = 0;
            LoaderInformation.Builder var30 = new LoaderInformation.Builder();
            if (var12 != null) {
               var30.setEntryPoint(var12);
            }

            var30.setWordSize(this.xK);
            var30.setEndianness(this.Dw);
            long var31 = this.xK();
            if (var2 != -1L && var31 != var2) {
               Uv.warn(S.L("Image base was determined to be: 0x%X. It is overridden to be: 0x%X"), var31, var2);
               var31 = var2;
            }

            var30.setImageBase(var31);
            var30.setEntryPoint(0L);
            var30.setImageSize(this.Dw() - var31);
            var30.setFlags(var27);
            this.setLoaderInformation(var30.build());
            if (this.q == null) {
               this.logWarn(true, S.L("The processor type was undetermined/not provided! Code of this ihex file will not be parsed."));
            } else {
               try {
                  String var33 = ProcessorUtil.toWellKnownUnitType(this.q);
                  if (var33 != null) {
                     IUnit var34 = this.getUnitProcessor().process(var33 + " image", this.getInput(), this, var33, true);
                     if (var34 != null) {
                        this.addChild(var34);
                     }
                  }
               } catch (Exception var21) {
                  Uv.catching(var21);
                  this.addNotification(new UnitNotification(NotificationType.UNSUPPORTED_FEATURE, S.L("The machine code was not disassembled")));
               }
            }
         }

         return true;
      } catch (IOException var23) {
         Uv.catching(var23);
         return false;
      }
   }

   private long xK() {
      long var1 = 0L;

      for (ISegmentInformation var4 : this.getSegments()) {
         if (var1 > var4.getOffsetInMemory()) {
            var1 = var4.getOffsetInMemory();
         }
      }

      return var1;
   }

   private long Dw() {
      long var1 = 0L;

      for (ISegmentInformation var4 : this.getSegments()) {
         if (var1 < var4.getOffsetInMemory() + var4.getSizeInMemory()) {
            var1 = var4.getOffsetInMemory() + var4.getSizeInMemory();
         }
      }

      return var1;
   }

   private void q(int var1) {
      if (this.getSegmentCount() >= 1) {
         SegmentInformation var2 = (SegmentInformation)this.getSegment(this.getSegmentCount() - 1);
         long var3 = var1 - var2.getOffsetInFile();
         var2.setSizeInFile(var3);
         var2.setSizeInMemory(var3);
      }
   }

   @Override
   public String getDescription() {
      StringBuilder var1 = new StringBuilder(super.getDescription());
      if (this.xK == 8) {
         var1.append("\nI8HEX");
      } else if (this.xK == 16) {
         var1.append("\nI16HEX");
      } else if (this.xK == 32) {
         var1.append("\nI32HEX");
      }

      return var1.toString();
   }

   @Override
   protected IInput getMappableInput() {
      return new BytesInput(this.RF);
   }

   @Override
   protected boolean shouldAllocateFullImage() {
      return false;
   }

   @Override
   protected boolean applyRelocations(IVirtualMemory var1, long var2, ILinkInfoProvider var4) {
      return true;
   }
}
