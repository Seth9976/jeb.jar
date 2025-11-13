package com.pnfsoftware.jeb.corei.parsers.wincoff;

import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.core.IUnitCreator;
import com.pnfsoftware.jeb.core.input.IInput;
import com.pnfsoftware.jeb.core.properties.IPropertyDefinitionManager;
import com.pnfsoftware.jeb.core.units.IUnit;
import com.pnfsoftware.jeb.core.units.IUnitProcessor;
import com.pnfsoftware.jeb.core.units.NotificationType;
import com.pnfsoftware.jeb.core.units.UnitNotification;
import com.pnfsoftware.jeb.core.units.code.asm.memory.IVirtualMemory;
import com.pnfsoftware.jeb.core.units.codeobject.AbstractCodeObjectUnit;
import com.pnfsoftware.jeb.core.units.codeobject.ICOFFHeader;
import com.pnfsoftware.jeb.core.units.codeobject.ICOFFSectionHeader;
import com.pnfsoftware.jeb.core.units.codeobject.ILinkInfoProvider;
import com.pnfsoftware.jeb.core.units.codeobject.IPECOFFUnit;
import com.pnfsoftware.jeb.core.units.codeobject.IPEOptionalHeader;
import com.pnfsoftware.jeb.core.units.codeobject.LoaderInformation;
import com.pnfsoftware.jeb.core.units.codeobject.ProcessorType;
import com.pnfsoftware.jeb.core.units.codeobject.SegmentInformation;
import com.pnfsoftware.jeb.core.units.codeobject.SymbolInformation;
import com.pnfsoftware.jeb.core.units.codeobject.SymbolType;
import com.pnfsoftware.jeb.corei.parsers.winpe.DH;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.io.EndianUtil;
import com.pnfsoftware.jeb.util.io.Endianness;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.SeekableByteChannel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Ser
public class K extends AbstractCodeObjectUnit implements IPECOFFUnit {
   private static final ILogger sY = GlobalLog.getLogger(K.class);
   @SerId(1)
   com.pnfsoftware.jeb.corei.parsers.winpe.K pC;
   @SerId(2)
   DH[] A;
   @SerId(3)
   Sv[] kS;
   @SerId(4)
   Map wS;
   @SerId(5)
   long UT;
   @SerId(6)
   boolean E;

   public K(String var1, IInput var2, IUnitProcessor var3, IUnitCreator var4, IPropertyDefinitionManager var5) {
      super(var2, "wincoff", var1, var3, var4, var5);
   }

   @Override
   public boolean isPE64() {
      return this.E;
   }

   @Override
   protected boolean processInternal() {
      long var1;
      try {
         label231: {
            int var5;
            try (SeekableByteChannel var3 = this.getInput().getChannel()) {
               var1 = var3.size();
               if (var1 <= 62L) {
                  return false;
               }

               ByteBuffer var4 = ByteBuffer.allocate(20).order(ByteOrder.LITTLE_ENDIAN);
               if (var3.read(var4) != 20) {
                  return false;
               }

               var4.rewind();
               this.pC = com.pnfsoftware.jeb.corei.parsers.winpe.K.pC(var4);
               if (this.pC.pC != 332 && this.pC.pC != 34404) {
                  Object[] var28 = new Object[0];
                  return false;
               }

               if (this.pC.pC == 34404) {
                  this.E = true;
               }

               if (this.pC.E != 0) {
                  Object[] var27 = new Object[0];
                  return false;
               }

               if (this.pC.UT == 0) {
                  Object[] var26 = new Object[0];
                  return false;
               }

               if (this.pC.A == 0) {
                  Object[] var25 = new Object[0];
                  return false;
               }

               if (!this.pC(var3)) {
                  return false;
               }

               if (this.A(var3)) {
                  var5 = 20;
                  if (this.A.length >= 1) {
                     int var6 = (int)this.A[0].UT;

                     for (int var7 = 1; var6 == 0 && var7 < this.A.length; var7++) {
                        var6 = (int)this.A[var7].UT;
                     }

                     if (var6 != 0 && var6 > var5) {
                        var5 = var6;
                     }
                  }

                  this.insertSegment(0, new SegmentInformation("<hdr>", 0L, var5, 0L, var5, 2));
                  break label231;
               }

               var5 = 0;
            }

            return (boolean)var5;
         }
      } catch (IOException var11) {
         Object[] var10000 = new Object[]{var11};
         return false;
      }

      LoaderInformation.Builder var12 = new LoaderInformation.Builder();
      int var13 = 8;
      var13 |= 32;
      var12.setFlags(var13);
      var12.setTargetProcessor(this.E ? ProcessorType.X86_64 : ProcessorType.X86);
      var12.setEndianness(Endianness.LITTLE_ENDIAN);
      var12.setWordSize(32);
      var12.setCompilationTimestamp(this.pC.getTimestampMs());
      var12.setImageBase(0L);
      var12.setImageSize(var1);
      this.setLoaderInformation(var12.build());

      try {
         String var17 = this.E ? "x86_64" : "x86";
         IUnit var24 = this.getUnitProcessor().process(var17 + "-image", this.getInput(), this, var17, true);
         if (var24 != null) {
            this.addChild(var24);
         }
      } catch (Exception var9) {
         sY.catching(var9);
         this.addNotification(new UnitNotification(NotificationType.UNSUPPORTED_FEATURE, S.L("The machine code was not disassembled")));
      }

      return true;
   }

   private boolean pC(SeekableByteChannel var1) throws IOException {
      this.A = new DH[this.pC.A];
      this.wS = new HashMap();
      var1.position(20L);
      ByteBuffer var2 = ByteBuffer.allocate(40).order(ByteOrder.LITTLE_ENDIAN);
      ArrayList var3 = new ArrayList();

      for (int var4 = 0; var4 < this.A.length; var4++) {
         var2.rewind();
         if (var1.read(var2) != 40) {
            return false;
         }

         var2.rewind();
         DH var5 = DH.pC(var2);
         this.A[var4] = var5;
         long var6 = var5.UT;
         long var8 = var5.wS;
         if (var5.A != 0L) {
            Object[] var16 = new Object[0];
            return false;
         }

         if (var5.kS != 0L) {
            Object[] var15 = new Object[0];
            return false;
         }

         String var10 = this.pC(var5.pC);
         if ((var5.gp & 128) != 0) {
            var3.add(var4);
         } else {
            int var11 = this.pC(var5.gp);
            this.addSegment(new SegmentInformation(var10, var6, var8, var6, var8, var11));
            Object[] var10000 = new Object[]{var10, var6, var6, var8};
            this.wS.put(var4, var6);
            this.UT = Math.max(this.UT, var8 + var6);
         }
      }

      if (var3.size() != 0) {
         for (Integer var13 : var3) {
            DH var14 = this.A[var13];
            int var7 = this.pC(var14.gp);
            this.addSegment(new SegmentInformation(this.pC(var14.pC), 0L, 0L, this.UT, var14.wS, var7));
            Object[] var17 = new Object[]{this.pC(var14.pC), this.UT, var14.wS};
            this.wS.put(var13, this.UT);
            this.UT = this.UT + var14.wS;
         }
      }

      return true;
   }

   private boolean A(SeekableByteChannel var1) throws IOException {
      this.kS = new Sv[this.pC.UT];
      this.addSegment(new SegmentInformation("<symboltable>", this.pC.wS, this.kS.length * 18, this.UT, this.kS.length * 18, 0));
      int var2 = this.A.length;
      this.wS.put(var2, this.UT);
      Object[] var10000 = new Object[]{this.UT, this.kS.length * 18};
      var1.position(this.pC.wS);
      ByteBuffer var3 = ByteBuffer.allocate(18).order(ByteOrder.LITTLE_ENDIAN);
      int var4 = 0;

      for (int var5 = 0; var5 < this.kS.length; var5++) {
         var3.rewind();
         if (var1.read(var3) != 18) {
            return false;
         }

         var3.rewind();
         if (var4 == 0) {
            Sv var6 = Sv.pC(var3);
            this.kS[var5] = var6;
            var4 = var6.E;
            String var7 = this.A(this.kS[var5].pC);
            var10000 = new Object[]{var5, var7, var6.wS, var6.UT, var6.kS, var6.A, var6.E};
            boolean var8 = false;
            if (this.pC()) {
               String var9 = this.pC(var7);
               if (var9 != null) {
                  long var10 = (Long)this.wS.get(this.kS[var5].kS - 1) + this.kS[var5].A;
                  SymbolInformation var12 = new SymbolInformation(SymbolType.FUNCTION, 0, 0L, var9, (Long)this.wS.get(var2) + var5 * 18, var10, 18L);
                  this.addSymbol(var12);
                  var8 = true;
               }
            }

            if (!var8 && Sv.A(var6)) {
               long var16 = (Long)this.wS.get(this.kS[var5].kS - 1) + this.kS[var5].A;
               SymbolInformation var17 = new SymbolInformation(SymbolType.FUNCTION, 0, 0L, var7, (Long)this.wS.get(var2) + var5 * 18, var16, 18L);
               this.addSymbol(var17);
            } else if (Sv.kS(var6)) {
               long var13 = (Long)this.wS.get(this.kS[var5].kS - 1) + this.kS[var5].A;
               SymbolInformation var11 = new SymbolInformation(SymbolType.UNKNOWN, 0, 0L, var7, (Long)this.wS.get(var2) + var5 * 18, var13, 18L);
               this.addSymbol(var11);
            } else if (Sv.UT(var6)) {
               SymbolInformation var14 = new SymbolInformation(
                  SymbolType.EXTERN_FUNCTION, 0, 0L, var7, (Long)this.wS.get(var2) + var5 * 18, (Long)this.wS.get(var2) + var5 * 18, 18L
               );
               this.addSymbol(var14);
            } else if (Sv.E(var6)) {
               SymbolInformation var15 = new SymbolInformation(
                  SymbolType.EXTERN_DATA, 0, 0L, var7, (Long)this.wS.get(var2) + var5 * 18, (Long)this.wS.get(var2) + var5 * 18, 18L
               );
               this.addSymbol(var15);
            }
         } else {
            var4--;
            var10000 = new Object[0];
         }
      }

      return true;
   }

   private boolean pC() {
      if (this.getParent() == null) {
         return false;
      } else {
         String var1 = this.getParent().getName();
         return var1.startsWith("libcrypto") || var1.startsWith("libssl") || var1.startsWith("libeay") || var1.startsWith("ssleay");
      }
   }

   private String pC(String var1) {
      return var1.startsWith("L$") && var1.endsWith("_begin") ? var1.substring(2, var1.length() - 6) : null;
   }

   private int pC(int var1) {
      int var2 = 0;
      var2 |= (var1 & 1073741824) != 0 ? 2 : 0;
      var2 |= (var1 & -2147483648) != 0 ? 1 : 0;
      return var2 | ((var1 & 536870912) != 0 ? 4 : 0);
   }

   public String pC(byte[] var1) {
      boolean var2 = false;
      if (var1[0] == 47) {
         var2 = true;
      }

      String var3;
      try {
         int var4 = 0;

         while (var4 < var1.length && var1[var4] != 0) {
            var4++;
         }

         var3 = Strings.decodeASCII(var1, 0, var4);
      } catch (Exception var5) {
         var3 = "";
      }

      if (var2) {
         int var6 = Integer.parseInt(var3.substring(1));
         var3 = this.A(var6);
      }

      return var3;
   }

   public String A(byte[] var1) {
      String var2;
      if (var1[0] == 0 && var1[1] == 0 && var1[2] == 0 && var1[3] == 0) {
         int var5 = EndianUtil.littleEndianBytesToInt(var1, 4);
         var2 = this.A(var5);
      } else {
         try {
            int var3 = 0;

            while (var3 < var1.length && var1[var3] != 0) {
               var3++;
            }

            var2 = Strings.decodeASCII(var1, 0, var3);
         } catch (Exception var4) {
            var2 = "";
         }
      }

      return var2;
   }

   private String A(int var1) {
      Object var2 = null;

      try {
         Object var12;
         try (SeekableByteChannel var3 = this.getInput().getChannel()) {
            var3.position(this.pC.wS + this.pC.UT * 18 + var1);
            int var4 = 1;
            ByteBuffer var5 = ByteBuffer.allocate(var4);
            if (var3.read(var5) != -1) {
               byte[] var6 = var5.array();

               while (var6[var6.length - 1] != 0) {
                  var5 = ByteBuffer.allocate(++var4);
                  var3.position(this.pC.wS + this.pC.UT * 18 + var1);
                  if (var3.read(var5) == -1) {
                     return null;
                  }

                  var6 = var5.array();
               }

               if (var6.length == 1) {
                  return "";
               }

               return Strings.decodeASCII(var6, 0, var4 - 1);
            }

            var12 = null;
         }

         return (String)var12;
      } catch (IOException var10) {
         Object[] var10000 = new Object[]{var10};
         return (String)var2;
      }
   }

   @Override
   protected boolean shouldAllocateFullImage() {
      return true;
   }

   @Override
   protected boolean applyRelocations(IVirtualMemory var1, long var2, ILinkInfoProvider var4) {
      try {
         try (SeekableByteChannel var5 = this.getInput().getChannel()) {
            for (int var6 = 0; var6 < this.A.length; var6++) {
               var5.position(this.A[var6].E);
               ByteBuffer var7 = ByteBuffer.allocate(10).order(ByteOrder.LITTLE_ENDIAN);

               for (int var8 = 0; var8 < this.A[var6].ys; var8++) {
                  var7.rewind();
                  if (var5.read(var7) != 10) {
                     return false;
                  }

                  var7.rewind();
                  Av var9 = Av.pC(var7);
                  Sv var10 = this.kS[var9.A];
                  long var11 = (Long)this.wS.get(var6) + var9.pC + var2;
                  int var13 = 0;
                  if (!this.E) {
                     var13 = var1.readLEInt(var11);
                  }

                  int var14;
                  if (Sv.UT(var10) || Sv.E(var10)) {
                     var14 = (int)(var2 + (Long)this.wS.get(this.A.length) + var9.A * 18);
                  } else if (!Sv.A(var10) && !Sv.pC(var10) && !Sv.kS(var10)) {
                     if (!Sv.wS(var10)) {
                        Object[] var27 = new Object[0];
                        continue;
                     }

                     var14 = (int)(var2 + (Long)this.wS.get(var10.kS - 1));
                  } else {
                     var14 = (int)(var2 + (Long)this.wS.get(var10.kS - 1) + var10.A);
                  }

                  var14 += var13;
                  if (!this.E) {
                     if (var9.kS == 20) {
                        int var15 = var14 - (int)var11 - 4;
                        Object[] var28 = new Object[]{(int)var11, var15};
                        var1.writeLEInt(var11, var15);
                     } else if (var9.kS == 6) {
                        var1.writeLEInt(var11, var14);
                     } else {
                        Object[] var29 = new Object[]{var9.kS, var11, var14};
                     }
                  } else if (var9.kS == 4) {
                     int var21 = var14 - (int)var11 - 4;
                     var1.writeLEInt(var11, var21);
                  } else if (var9.kS == 5) {
                     int var22 = var14 - (int)var11 - 4 - 1;
                     var1.writeLEInt(var11, var22);
                  } else if (var9.kS == 6) {
                     int var23 = var14 - (int)var11 - 4 - 2;
                     var1.writeLEInt(var11, var23);
                  } else if (var9.kS == 7) {
                     int var24 = var14 - (int)var11 - 4 - 3;
                     var1.writeLEInt(var11, var24);
                  } else if (var9.kS == 8) {
                     int var25 = var14 - (int)var11 - 4 - 4;
                     var1.writeLEInt(var11, var25);
                  } else if (var9.kS == 9) {
                     int var26 = var14 - (int)var11 - 4 - 5;
                     var1.writeLEInt(var11, var26);
                  } else {
                     Object[] var30 = new Object[]{var9.kS, var11, var14};
                  }
               }
            }
         }

         return true;
      } catch (IOException var18) {
         Object[] var10000 = new Object[]{var18};
         return false;
      }
   }

   @Override
   public ICOFFHeader getCOFFHeader() {
      return this.pC;
   }

   @Override
   public IPEOptionalHeader getPEOptionalHeader() {
      return null;
   }

   @Override
   public ICOFFSectionHeader[] getSectionHeaders() {
      return this.A;
   }
}
