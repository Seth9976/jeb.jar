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
import com.pnfsoftware.jeb.corei.parsers.winpe.iZ;
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
public class nI extends AbstractCodeObjectUnit implements IPECOFFUnit {
   private static final ILogger gO = GlobalLog.getLogger(nI.class);
   @SerId(1)
   com.pnfsoftware.jeb.corei.parsers.winpe.nI q;
   @SerId(2)
   iZ[] RF;
   @SerId(3)
   CU[] xK;
   @SerId(4)
   Map Dw;
   @SerId(5)
   long Uv;
   @SerId(6)
   boolean oW;

   public nI(String var1, IInput var2, IUnitProcessor var3, IUnitCreator var4, IPropertyDefinitionManager var5) {
      super(var2, "wincoff", var1, var3, var4, var5);
   }

   @Override
   public boolean isPE64() {
      return this.oW;
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
               this.q = com.pnfsoftware.jeb.corei.parsers.winpe.nI.q(var4);
               if (this.q.RF != 332 && this.q.RF != 34404) {
                  Object[] var28 = new Object[0];
                  return false;
               }

               if (this.q.RF == 34404) {
                  this.oW = true;
               }

               if (this.q.gO != 0) {
                  Object[] var27 = new Object[0];
                  return false;
               }

               if (this.q.oW == 0) {
                  Object[] var26 = new Object[0];
                  return false;
               }

               if (this.q.xK == 0) {
                  Object[] var25 = new Object[0];
                  return false;
               }

               if (!this.q(var3)) {
                  return false;
               }

               if (this.RF(var3)) {
                  var5 = 20;
                  if (this.RF.length >= 1) {
                     int var6 = (int)this.RF[0].oW;

                     for (int var7 = 1; var6 == 0 && var7 < this.RF.length; var7++) {
                        var6 = (int)this.RF[var7].oW;
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
      var12.setTargetProcessor(this.oW ? ProcessorType.X86_64 : ProcessorType.X86);
      var12.setEndianness(Endianness.LITTLE_ENDIAN);
      var12.setWordSize(32);
      var12.setCompilationTimestamp(this.q.getTimestampMs());
      var12.setImageBase(0L);
      var12.setImageSize(var1);
      this.setLoaderInformation(var12.build());

      try {
         String var17 = this.oW ? "x86_64" : "x86";
         IUnit var24 = this.getUnitProcessor().process(var17 + " image", this.getInput(), this, var17, true);
         if (var24 != null) {
            this.addChild(var24);
         }
      } catch (Exception var9) {
         gO.catching(var9);
         this.addNotification(new UnitNotification(NotificationType.UNSUPPORTED_FEATURE, S.L("The machine code was not disassembled")));
      }

      return true;
   }

   private boolean q(SeekableByteChannel var1) throws IOException {
      this.RF = new iZ[this.q.xK];
      this.Dw = new HashMap();
      var1.position(20L);
      ByteBuffer var2 = ByteBuffer.allocate(40).order(ByteOrder.LITTLE_ENDIAN);
      ArrayList var3 = new ArrayList();

      for (int var4 = 0; var4 < this.RF.length; var4++) {
         var2.rewind();
         if (var1.read(var2) != 40) {
            return false;
         }

         var2.rewind();
         iZ var5 = iZ.q(var2);
         this.RF[var4] = var5;
         long var6 = var5.oW;
         long var8 = var5.Uv;
         if (var5.xK != 0L) {
            Object[] var16 = new Object[0];
            return false;
         }

         if (var5.Dw != 0L) {
            Object[] var15 = new Object[0];
            return false;
         }

         String var10 = this.q(var5.RF);
         if ((var5.lm & 128) != 0) {
            var3.add(var4);
         } else {
            int var11 = this.q(var5.lm);
            this.addSegment(new SegmentInformation(var10, var6, var8, var6, var8, var11));
            Object[] var10000 = new Object[]{var10, var6, var6, var8};
            this.Dw.put(var4, var6);
            this.Uv = Math.max(this.Uv, var8 + var6);
         }
      }

      if (var3.size() != 0) {
         for (Integer var13 : var3) {
            iZ var14 = this.RF[var13];
            int var7 = this.q(var14.lm);
            this.addSegment(new SegmentInformation(this.q(var14.RF), 0L, 0L, this.Uv, var14.Uv, var7));
            Object[] var17 = new Object[]{this.q(var14.RF), this.Uv, var14.Uv};
            this.Dw.put(var13, this.Uv);
            this.Uv = this.Uv + var14.Uv;
         }
      }

      return true;
   }

   private boolean RF(SeekableByteChannel var1) throws IOException {
      this.xK = new CU[this.q.oW];
      this.addSegment(new SegmentInformation("<symboltable>", this.q.Uv, this.xK.length * 18, this.Uv, this.xK.length * 18, 0));
      int var2 = this.RF.length;
      this.Dw.put(var2, this.Uv);
      Object[] var10000 = new Object[]{this.Uv, this.xK.length * 18};
      var1.position(this.q.Uv);
      ByteBuffer var3 = ByteBuffer.allocate(18).order(ByteOrder.LITTLE_ENDIAN);
      int var4 = 0;

      for (int var5 = 0; var5 < this.xK.length; var5++) {
         var3.rewind();
         if (var1.read(var3) != 18) {
            return false;
         }

         var3.rewind();
         if (var4 == 0) {
            CU var6 = CU.q(var3);
            this.xK[var5] = var6;
            var4 = var6.qa;
            String var7 = this.RF(this.xK[var5].zz);
            var10000 = new Object[]{var5, var7, var6.LK, var6.io, var6.HF, var6.JY, var6.qa};
            boolean var8 = false;
            if (this.q()) {
               String var9 = this.q(var7);
               if (var9 != null) {
                  long var10 = (Long)this.Dw.get(this.xK[var5].HF - 1) + this.xK[var5].JY;
                  SymbolInformation var12 = new SymbolInformation(SymbolType.FUNCTION, 0, 0L, var9, (Long)this.Dw.get(var2) + var5 * 18, var10, 18L);
                  this.addSymbol(var12);
                  var8 = true;
               }
            }

            if (!var8 && CU.RF(var6)) {
               long var16 = (Long)this.Dw.get(this.xK[var5].HF - 1) + this.xK[var5].JY;
               SymbolInformation var17 = new SymbolInformation(SymbolType.FUNCTION, 0, 0L, var7, (Long)this.Dw.get(var2) + var5 * 18, var16, 18L);
               this.addSymbol(var17);
            } else if (CU.xK(var6)) {
               long var13 = (Long)this.Dw.get(this.xK[var5].HF - 1) + this.xK[var5].JY;
               SymbolInformation var11 = new SymbolInformation(SymbolType.UNKNOWN, 0, 0L, var7, (Long)this.Dw.get(var2) + var5 * 18, var13, 18L);
               this.addSymbol(var11);
            } else if (CU.Uv(var6)) {
               SymbolInformation var14 = new SymbolInformation(
                  SymbolType.EXTERN_FUNCTION, 0, 0L, var7, (Long)this.Dw.get(var2) + var5 * 18, (Long)this.Dw.get(var2) + var5 * 18, 18L
               );
               this.addSymbol(var14);
            } else if (CU.oW(var6)) {
               SymbolInformation var15 = new SymbolInformation(
                  SymbolType.EXTERN_DATA, 0, 0L, var7, (Long)this.Dw.get(var2) + var5 * 18, (Long)this.Dw.get(var2) + var5 * 18, 18L
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

   private boolean q() {
      if (this.getParent() == null) {
         return false;
      } else {
         String var1 = this.getParent().getName();
         return var1.startsWith("libcrypto") || var1.startsWith("libssl") || var1.startsWith("libeay") || var1.startsWith("ssleay");
      }
   }

   private String q(String var1) {
      return var1.startsWith("L$") && var1.endsWith("_begin") ? var1.substring(2, var1.length() - 6) : null;
   }

   private int q(int var1) {
      int var2 = 0;
      var2 |= (var1 & 1073741824) != 0 ? 2 : 0;
      var2 |= (var1 & -2147483648) != 0 ? 1 : 0;
      return var2 | ((var1 & 536870912) != 0 ? 4 : 0);
   }

   public String q(byte[] var1) {
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
         var3 = this.RF(var6);
      }

      return var3;
   }

   public String RF(byte[] var1) {
      String var2;
      if (var1[0] == 0 && var1[1] == 0 && var1[2] == 0 && var1[3] == 0) {
         int var5 = EndianUtil.littleEndianBytesToInt(var1, 4);
         var2 = this.RF(var5);
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

   private String RF(int var1) {
      Object var2 = null;

      try {
         Object var12;
         try (SeekableByteChannel var3 = this.getInput().getChannel()) {
            var3.position(this.q.Uv + this.q.oW * 18 + var1);
            int var4 = 1;
            ByteBuffer var5 = ByteBuffer.allocate(var4);
            if (var3.read(var5) != -1) {
               byte[] var6 = var5.array();

               while (var6[var6.length - 1] != 0) {
                  var5 = ByteBuffer.allocate(++var4);
                  var3.position(this.q.Uv + this.q.oW * 18 + var1);
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
            for (int var6 = 0; var6 < this.RF.length; var6++) {
               var5.position(this.RF[var6].gO);
               ByteBuffer var7 = ByteBuffer.allocate(10).order(ByteOrder.LITTLE_ENDIAN);

               for (int var8 = 0; var8 < this.RF[var6].gP; var8++) {
                  var7.rewind();
                  if (var5.read(var7) != 10) {
                     return false;
                  }

                  var7.rewind();
                  eo var9 = eo.q(var7);
                  CU var10 = this.xK[var9.lm];
                  long var11 = (Long)this.Dw.get(var6) + var9.za + var2;
                  int var13 = 0;
                  if (!this.oW) {
                     var13 = var1.readLEInt(var11);
                  }

                  int var14;
                  if (CU.Uv(var10) || CU.oW(var10)) {
                     var14 = (int)(var2 + (Long)this.Dw.get(this.RF.length) + var9.lm * 18);
                  } else if (!CU.RF(var10) && !CU.q(var10) && !CU.xK(var10)) {
                     if (!CU.Dw(var10)) {
                        Object[] var27 = new Object[0];
                        continue;
                     }

                     var14 = (int)(var2 + (Long)this.Dw.get(var10.HF - 1));
                  } else {
                     var14 = (int)(var2 + (Long)this.Dw.get(var10.HF - 1) + var10.JY);
                  }

                  var14 += var13;
                  if (!this.oW) {
                     if (var9.zz == 20) {
                        int var15 = var14 - (int)var11 - 4;
                        Object[] var28 = new Object[]{(int)var11, var15};
                        var1.writeLEInt(var11, var15);
                     } else if (var9.zz == 6) {
                        var1.writeLEInt(var11, var14);
                     } else {
                        Object[] var29 = new Object[]{var9.zz, var11, var14};
                     }
                  } else if (var9.zz == 4) {
                     int var21 = var14 - (int)var11 - 4;
                     var1.writeLEInt(var11, var21);
                  } else if (var9.zz == 5) {
                     int var22 = var14 - (int)var11 - 4 - 1;
                     var1.writeLEInt(var11, var22);
                  } else if (var9.zz == 6) {
                     int var23 = var14 - (int)var11 - 4 - 2;
                     var1.writeLEInt(var11, var23);
                  } else if (var9.zz == 7) {
                     int var24 = var14 - (int)var11 - 4 - 3;
                     var1.writeLEInt(var11, var24);
                  } else if (var9.zz == 8) {
                     int var25 = var14 - (int)var11 - 4 - 4;
                     var1.writeLEInt(var11, var25);
                  } else if (var9.zz == 9) {
                     int var26 = var14 - (int)var11 - 4 - 5;
                     var1.writeLEInt(var11, var26);
                  } else {
                     Object[] var30 = new Object[]{var9.zz, var11, var14};
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
      return this.q;
   }

   @Override
   public IPEOptionalHeader getPEOptionalHeader() {
      return null;
   }

   @Override
   public ICOFFSectionHeader[] getSectionHeaders() {
      return this.RF;
   }
}
