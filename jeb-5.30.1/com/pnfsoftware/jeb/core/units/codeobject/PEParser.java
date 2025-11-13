package com.pnfsoftware.jeb.core.units.codeobject;

import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.core.input.IInput;
import com.pnfsoftware.jeb.corei.parsers.winpe.Nt;
import com.pnfsoftware.jeb.corei.parsers.winpe.eo;
import com.pnfsoftware.jeb.corei.parsers.winpe.iZ;
import com.pnfsoftware.jeb.corei.parsers.winpe.nI;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.io.ChannelUtil;
import com.pnfsoftware.jeb.util.io.Endianness;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.SeekableByteChannel;
import java.util.ArrayList;
import java.util.List;

public class PEParser {
   private static final ILogger logger = GlobalLog.getLogger(PEParser.class);
   nI filehdr;
   Nt opthdr;
   iZ[] sectab;
   private ILoaderInformation ldInfo = new LoaderInformation();
   private List segInfo = new ArrayList();
   private List secInfo = new ArrayList();
   private boolean is64Bit;
   private boolean isLowAlignment;

   public PEParser(IInput var1) throws IOException {
      long var2 = 0L;

      try (SeekableByteChannel var4 = var1.getChannel()) {
         long var5 = var4.size();
         if (var5 <= 64L) {
            throw new IOException();
         }

         if (ChannelUtil.getLEShort(var4, 0L) != 23117) {
            throw new IOException();
         }

         int var7 = ChannelUtil.getLEInt(var4, 60L);
         if (var7 < 0 || var7 + 4 > var5) {
            throw new IOException();
         }

         if (ChannelUtil.getLEInt(var4, var7) != 17744) {
            throw new IOException();
         }

         ByteBuffer var8 = ByteBuffer.allocate(20).order(ByteOrder.LITTLE_ENDIAN);
         var4.position(var7 + 4);
         if (var4.read(var8) != 20) {
            throw new IOException();
         }

         var8.rewind();
         this.filehdr = nI.q(var8);
         short var9 = ChannelUtil.getLEShort(var4, var7 + 4 + 20);
         if (var9 == 523) {
            this.is64Bit = true;
         } else if (var9 != 267) {
            throw new IOException(Strings.ff("Invalid optional header magic: %X", var9));
         }

         var8 = ByteBuffer.allocate(512).order(ByteOrder.LITTLE_ENDIAN);
         var4.position(var7 + 4 + 20);
         int var10 = var4.read(var8);
         if (var10 < 69) {
            throw new IOException();
         }

         var8.rewind();
         var8.limit(var10);
         this.opthdr = Nt.q(var8, this.is64Bit);
         int var11 = this.opthdr.zz;
         int var12 = this.opthdr.lm;
         if (!this.isPowerOfTwo(var11) || !this.isPowerOfTwo(var12)) {
            throw new IOException(Strings.ff("Invalid alignments, must be a power of 2 (file=%Xh, section=%Xh)", var11, var12));
         }

         if (var12 <= 4096) {
            this.isLowAlignment = true;
         }

         if (var11 > var12) {
            throw new IOException(Strings.ff("Invalid alignments (file=%Xh, section=%Xh)", var11, var12));
         }

         this.sectab = new iZ[this.filehdr.xK];
         if (this.sectab.length > 0) {
            var4.position(var7 + 4 + 20 + this.filehdr.gO);
            var8 = ByteBuffer.allocate(40).order(ByteOrder.LITTLE_ENDIAN);
            long var13 = -1L;

            for (int var15 = 0; var15 < this.sectab.length; var15++) {
               var8.rewind();
               if (var4.read(var8) != 40) {
                  throw new IOException();
               }

               var8.rewind();
               iZ var16 = iZ.q(var8);
               this.sectab[var15] = var16;
               long var17 = var16.oW;
               long var19 = var16.Uv;
               long var21 = var16.xK;
               long var23;
               long var48;
               if (!this.isLowAlignment) {
                  var23 = var17 & -512L;
                  var48 = (var17 + var19 + var11 - 1L & ~(var11 - 1)) - var23;
                  var48 = Math.min(var48, var19 + 4095L & -4096L);
                  if (var21 != 0L) {
                     var48 = Math.min(var48, var21 + 4095L & -4096L);
                  }
               } else {
                  var23 = var17 & ~(var11 - 1);
                  var48 = var19;
                  if (var21 != 0L) {
                     var48 = Math.min(var19, var21 + var12 - 1L & ~(var12 - 1));
                  }
               }

               if (var48 > 2147483647L) {
                  throw new RuntimeException("Section is too large");
               }

               long var27 = var23 + var48;
               if (var27 > var2) {
                  var2 = var27;
               }

               long var29 = var16.Dw;
               if (var29 % var12 != 0L) {
                  throw new IOException("Invalid segment memory address");
               }

               if (var21 == 0L) {
                  var21 = var48;
               }

               if (var13 != -1L && var13 != var29) {
                  logger.warn(S.L("Illegal section, PE unlikely to load: expected RVA= %Xh, actual=%Xh"), var13, var29);
               }

               long var31 = (var21 + var12 - 1L) / var12 * var12;
               var13 = var29 + var31;

               String var33;
               try {
                  int var34 = 0;

                  while (var34 < var16.RF.length && var16.RF[var34] != 0) {
                     var34++;
                  }

                  var33 = Strings.decodeASCII(var16.RF, 0, var34);
               } catch (Exception var36) {
                  var33 = "";
               }

               int var49 = this.sectionCharacteristicsToGenericFlags(var16.lm);
               this.segInfo.add(new SegmentInformation(var33, var23, var48, var29, var21, var49));
            }
         }

         int var45 = (int)var4.size();
         if (this.sectab.length >= 1) {
            int var14 = (int)this.sectab[0].oW;

            for (int var47 = 1; var14 == 0 && var47 < this.sectab.length; var47++) {
               var14 = (int)this.sectab[var47].oW;
            }

            if (var14 != 0 && var14 < var45) {
               var45 = var14;
            }
         }

         int var46 = (int)this.opthdr.oQ;
         if (var46 < var45) {
            var45 = var46;
         }

         this.segInfo.add(0, new SegmentInformation("<hdr>", 0L, var45, 0L, var45, 2));
      }

      LoaderInformation.Builder var38 = new LoaderInformation.Builder();
      byte var39 = 0;
      if ((this.filehdr.nf & 8192) != 0) {
         var39 |= 4;
      }

      var38.setFlags(var39);
      var38.setTargetProcessor(this.machineToProcessorType(this.filehdr.RF));
      var38.setTargetSubsystem(this.subsystemToSubsystemType(this.opthdr.KT));
      var38.setEndianness(this.machineToEndianness(this.filehdr.RF));
      var38.setWordSize(this.is64Bit ? 64 : 32);
      var38.setCompilationTimestamp(this.filehdr.getTimestampMs());
      var38.setImageBase(this.opthdr.za);
      var38.setImageSize(this.opthdr.PV);
      var38.setEntryPoint(this.opthdr.gO);
      var38.setOverlayOffset(var2);
      this.ldInfo = var38.build();

      for (int var6 = 0; var6 < this.opthdr.Dz.length; var6++) {
         eo var40 = this.opthdr.Dz[var6];
         String var43 = var6 < eo.q.length ? eo.q[var6] : "-";
         long var44 = 0L;
         if (var40.xK != 0L) {
            var44 = this.convertRelativeAddressToFileOffset(var40.xK);
         }

         this.secInfo.add(new SegmentInformation(var43, var44, var40.Dw, var40.xK, var40.Dw, 0));
      }
   }

   private boolean isPowerOfTwo(int var1) {
      return var1 > 0 && (var1 & var1 - 1) == 0;
   }

   private int sectionCharacteristicsToGenericFlags(int var1) {
      int var2 = 0;
      var2 |= (var1 & 1073741824) != 0 ? 2 : 0;
      var2 |= (var1 & -2147483648) != 0 ? 1 : 0;
      return var2 | ((var1 & 536870912) != 0 ? 4 : 0);
   }

   private ProcessorType machineToProcessorType(int var1) {
      switch (var1) {
         case 332:
            return ProcessorType.X86;
         case 352:
            return ProcessorType.MIPS;
         case 354:
         case 358:
         case 360:
         case 361:
         case 870:
            return ProcessorType.MIPS;
         case 448:
         case 450:
         case 452:
            return ProcessorType.ARM;
         case 34404:
            return ProcessorType.X86_64;
         case 43620:
            return ProcessorType.ARM64;
         default:
            return null;
      }
   }

   private Endianness machineToEndianness(int var1) {
      switch (var1) {
         case 352:
            return Endianness.BIG_ENDIAN;
         default:
            return Endianness.LITTLE_ENDIAN;
      }
   }

   private SubsystemType subsystemToSubsystemType(int var1) {
      switch (var1) {
         case 2:
         case 3:
            return SubsystemType.WINDOWS_USER;
         case 8:
            return SubsystemType.WINDOWS_KERNEL;
         default:
            return null;
      }
   }

   public long add(long var1, long var3) {
      return this.sanitizeAddress(var1 + var3);
   }

   public long sanitizeAddress(long var1) {
      return this.is64Bit ? var1 : var1 & 4294967295L;
   }

   public boolean isAddressInside(long var1) {
      return var1 >= this.opthdr.za && var1 < this.add(this.opthdr.za, this.opthdr.PV);
   }

   public long convertFileOffsetToRelativeAddress(long var1) {
      for (iZ var6 : this.sectab) {
         if (var1 >= var6.oW && var1 <= var6.oW + var6.Uv) {
            return var6.Dw + (var1 - var6.oW);
         }
      }

      return -1L;
   }

   public long convertRelativeAddressToFileOffset(long var1) {
      for (iZ var6 : this.sectab) {
         if (var1 >= var6.Dw && var1 < var6.Dw + var6.xK) {
            return var6.oW + (var1 - var6.Dw);
         }
      }

      return -1L;
   }

   public ICOFFHeader getCOFFHeader() {
      return this.filehdr;
   }

   public IPEOptionalHeader getPEOptionalHeader() {
      return this.opthdr;
   }

   public ICOFFSectionHeader[] getSectionHeaders() {
      return this.sectab;
   }

   public List getSegments() {
      return this.segInfo;
   }

   public int getSegmentCount() {
      return this.segInfo.size();
   }

   public ISegmentInformation getSegment(int var1) {
      return (ISegmentInformation)this.segInfo.get(var1);
   }
}
