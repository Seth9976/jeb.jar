package com.pnfsoftware.jeb.corei.parsers.wasm;

import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.core.IUnitCreator;
import com.pnfsoftware.jeb.core.JebCoreService;
import com.pnfsoftware.jeb.core.input.IInput;
import com.pnfsoftware.jeb.core.properties.IPropertyDefinitionManager;
import com.pnfsoftware.jeb.core.units.IUnit;
import com.pnfsoftware.jeb.core.units.IUnitProcessor;
import com.pnfsoftware.jeb.core.units.NotificationType;
import com.pnfsoftware.jeb.core.units.UnitNotification;
import com.pnfsoftware.jeb.core.units.code.asm.memory.IVirtualMemory;
import com.pnfsoftware.jeb.core.units.code.asm.memory.MemoryException;
import com.pnfsoftware.jeb.core.units.codeobject.AbstractCodeObjectUnit;
import com.pnfsoftware.jeb.core.units.codeobject.CodeObjectUnitUtil;
import com.pnfsoftware.jeb.core.units.codeobject.ILinkInfoProvider;
import com.pnfsoftware.jeb.core.units.codeobject.LoaderInformation;
import com.pnfsoftware.jeb.core.units.codeobject.ProcessorType;
import com.pnfsoftware.jeb.core.units.codeobject.SegmentInformation;
import com.pnfsoftware.jeb.core.units.codeobject.SymbolInformation;
import com.pnfsoftware.jeb.core.units.codeobject.SymbolType;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.io.Endianness;
import com.pnfsoftware.jeb.util.io.IO;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import com.pnfsoftware.jebglobal.chl;
import com.pnfsoftware.jebglobal.chm;
import com.pnfsoftware.jebglobal.chn;
import com.pnfsoftware.jebglobal.cho;
import com.pnfsoftware.jebglobal.chp;
import com.pnfsoftware.jebglobal.chq;
import com.pnfsoftware.jebglobal.chu;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

@Ser
public class Hv extends AbstractCodeObjectUnit {
   private static final ILogger WR = GlobalLog.getLogger(Hv.class);
   @SerId(1)
   byte[] pC;
   @SerId(2)
   gJ A;
   @SerId(3)
   List kS;
   @SerId(4)
   List wS = new ArrayList();
   @SerId(5)
   private List NS = new ArrayList();
   @SerId(6)
   private List vP = new ArrayList();
   @SerId(7)
   private List xC = new ArrayList();
   @SerId(8)
   private List ED = new ArrayList();
   @SerId(9)
   private List Sc = new ArrayList();
   @SerId(10)
   chn UT = new chn();
   @SerId(11)
   cho E = new cho();
   @SerId(12)
   chu sY = new chu();
   @SerId(13)
   chp ys = new chp();
   @SerId(14)
   List ld = new ArrayList();
   @SerId(15)
   Integer gp;
   @SerId(16)
   List oT = new ArrayList();
   @SerId(17)
   List fI = new ArrayList();

   public Hv(String var1, IInput var2, IUnitProcessor var3, IUnitCreator var4, IPropertyDefinitionManager var5) {
      super(var2, "wasm", var1, var3, var4, var5);
   }

   public gJ pC() {
      return this.A;
   }

   @Override
   protected boolean processInternal() {
      try {
         boolean var67;
         try (InputStream var1 = this.getInput().getStream()) {
            this.pC = IO.readInputStream(var1);
            int var2 = this.pC.length;
            this.A = new gJ(this);
            int var3 = 8;
            this.kS = new ArrayList();

            for (int var4 = 0; var3 < var2; var4++) {
               int var5 = uX.pC(this.pC, var3);
               uX.Av var6 = uX.E(this.pC, ++var3);
               int var7 = (int)var6.pC;
               var3 += var6.A;
               String var8 = null;
               if (var5 == 0) {
                  var6 = uX.E(this.pC, var3);
                  int var9 = (int)var6.pC;
                  var3 += var6.A;
                  var7 = var7 - var6.A - var9;
                  var8 = new String(this.pC, var3, var9, Charset.forName("UTF-8"));
                  var3 += var9;
               }

               GK var34 = new GK(var5, var8, var3, var7);
               this.kS.add(var34);
               Object[] var10000 = new Object[]{var4, var34};
               var3 += var7;
            }

            int var27 = 0;

            for (GK var30 : this.kS) {
               String var31;
               if (var30.pC == 0) {
                  this.logInfo(true, "Skipping processing of custom section: %s", var30.A);
                  var31 = Strings.safe(var30.A);
               } else {
                  switch (var30.pC) {
                     case 1:
                        this.pC(this.pC, var30);
                        break;
                     case 2:
                        this.A(this.pC, var30);
                        break;
                     case 3:
                        var27 = this.kS(this.pC, var30);
                        this.UT();
                        break;
                     case 4:
                        this.wS(this.pC, var30);
                        this.UT();
                        break;
                     case 5:
                        this.UT(this.pC, var30);
                        this.UT();
                        break;
                     case 6:
                        this.E(this.pC, var30);
                        this.UT();
                        break;
                     case 7:
                        this.sY(this.pC, var30);
                        this.UT();
                        break;
                     case 8:
                        this.ys(this.pC, var30);
                        break;
                     case 9:
                        this.ld(this.pC, var30);
                        break;
                     case 10:
                        this.pC(this.pC, var30, var27);
                        break;
                     case 11:
                        this.gp(this.pC, var30);
                        break;
                     case 12:
                        this.oT(this.pC, var30);
                        break;
                     default:
                        throw new RuntimeException("Unprocessed section: id=" + var30.pC);
                  }

                  var31 = Tb.pC(var30.pC);
               }

               this.addSection(new SegmentInformation(var31, var30.kS, var30.wS, 0L, 0L, 2));
            }

            if (this.ys.pC() >= 1) {
               if (this.ys.pC() >= 2) {
                  throw new chm("2+ memories is not supported");
               }

               oP var72 = (oP)this.ys.pC(0);
            }

            if (this.sY.pC() >= 1) {
               if (this.sY.pC() >= 2) {
                  throw new chm("2+ tables is not supported");
               }

               Mh var73 = (Mh)this.sY.pC(0);
            }

            long var32 = 1L;

            for (Sv var11 : this.fI) {
               try {
                  long var12 = 0L + (var11.A == null ? 0L : var11.A.pC(this));
                  long var14 = var12 + var11.wS;
                  if (var14 > var32) {
                     var32 = var14;
                  }
               } catch (chl var21) {
                  WR.catchingSilent(var21);
               }
            }

            int var35 = (int)(var32 - 0L);
            if (var35 > 0) {
               this.addSegment(new SegmentInformation(".data", 0L, 0L, 0L, var35, 7));
            }

            var32 = 1L;

            for (K var45 : this.oT) {
               if (var45.pC != 0) {
                  throw new chm();
               }

               long var13 = 1073741824L + 4L * var45.A.pC(this);

               for (int var16 : var45.kS) {
                  cq var17 = this.pC(var16);
                  SymbolInformation var18 = new SymbolInformation(SymbolType.PTRFUNCTION, var17.UT() ? 1 : 0, 0L, var17.ys(), 0L, var13, 4L);
                  this.addSymbol(var18);
                  var13 += 4L;
                  if (var13 > var32) {
                     var32 = var13;
                  }
               }
            }

            var35 = (int)(var32 - 1073741824L);
            if (var35 > 0) {
               this.addSegment(new SegmentInformation(".table", 0L, 0L, 1073741824L, var35, 7));
            }

            long var41 = 1342177280L;

            for (cq var51 : this.UT.wS()) {
               var51.kS = var41;
               var51.wS = var51.A.pC();
               this.addSymbol(new SymbolInformation(SymbolType.FUNCTION, 4, var51.wS(), var51.ys(), 0L, var51.kS, var51.wS));
               var41 += var51.wS;
            }

            var35 = (int)(var41 - 1342177280L);
            if (var35 > 0) {
               this.addSegment(new SegmentInformation(".code", 0L, 0L, 1342177280L, var35, 7));
            }

            var41 = 1610612736L;

            for (rQ var52 : this.E.wS()) {
               String var59 = var52.ys();
               if (!var52.sY()) {
                  Integer var68 = var52.pC();
                  if (var68 != null) {
                     rQ var71 = (rQ)this.E.pC(var68);
                     if (var71 != null && var71.sY()) {
                        var59 = "__" + var71.ld();
                     }
                  }
               }

               var52.kS = var41;
               var52.wS = Tb.sY(var52.pC.pC);
               SymbolInformation var69 = new SymbolInformation(SymbolType.VARIABLE, 0, var52.wS(), var59, 0L, var52.kS, var52.wS);
               var69.setSymbolDataTypeInformation(Tb.ys(var52.pC.pC));
               this.addSymbol(var69);
               var41 += var52.wS;
            }

            var35 = (int)(var41 - 1610612736L);
            if (var35 > 0) {
               this.addSegment(new SegmentInformation(".globals", 0L, 0L, 1610612736L, var35, 7));
            }

            var41 = 1879048192L;

            for (rQ var53 : this.E.UT()) {
               var53.kS = var41;
               SymbolInformation var60 = new SymbolInformation(SymbolType.PTRVARIABLE, 1, var53.wS(), var53.ys(), 0L, var41, 4L);
               this.addSymbol(var60);
               var41 += 4L;
            }

            var41 = var41 + 15L & -16L;

            for (cq var54 : this.UT.UT()) {
               var54.kS = var41;
               SymbolInformation var61 = new SymbolInformation(SymbolType.PTRFUNCTION, 1, var54.wS(), var54.ys(), 0L, var41, 4L);
               this.addSymbol(var61);
               var41 += 4L;
            }

            var35 = (int)(var41 - 1879048192L);
            if (var35 > 0) {
               this.addSegment(new SegmentInformation(".imports", 0L, 0L, 1879048192L, var35, 7));
            }

            LoaderInformation var50 = new LoaderInformation.Builder()
               .setVersion("1")
               .setTargetProcessor(ProcessorType.UNKNOWN)
               .setWordSize(32)
               .setEndianness(Endianness.LITTLE_ENDIAN)
               .setImageBase(0L)
               .setImageSize(2147483648L)
               .setFlags(4)
               .build();
            this.setLoaderInformation(var50);
            Object[] var74 = new Object[0];
            var74 = new Object[]{this.UT.pC(), this.UT.A(), this.UT.kS()};
            var74 = new Object[]{this.E.pC(), this.E.A(), this.E.kS()};
            var74 = new Object[]{this.ys.pC(), this.ys.A(), this.ys.kS()};
            var74 = new Object[]{this.sY.pC(), this.sY.A(), this.sY.kS()};
            var74 = new Object[0];

            for (rQ var62 : this.E) {
               var74 = new Object[]{var62};
            }

            var74 = new Object[0];

            for (Mh var63 : this.sY) {
               var74 = new Object[]{var63};
            }

            var74 = new Object[0];

            for (oP var64 : this.ys) {
               var74 = new Object[]{var64};
            }

            var74 = new Object[0];

            for (cq var65 : this.UT.wS()) {
               var74 = new Object[]{var65};
               var65.kS();
               var74 = new Object[0];
            }

            try {
               String var66 = "wasmbc";
               IUnit var70 = this.getUnitProcessor().process(var66 + "-image", this.getInput(), this, var66, true);
               if (var70 != null) {
                  this.addChild(var70);
               }
            } catch (Exception var20) {
               WR.catching(var20);
               this.addNotification(new UnitNotification(NotificationType.UNSUPPORTED_FEATURE, S.L("The bytecode was not disassembled")));
            }

            var67 = true;
         }

         return var67;
      } catch (Exception var23) {
         WR.catching(var23);
         return false;
      }
   }

   @Override
   protected boolean mapRawNoReloc(IVirtualMemory var1, long var2) {
      super.mapRawNoReloc(var1, var2);
      if (var2 != 0L) {
         throw new IllegalArgumentException("Must map at address 0");
      } else {
         try {
            if (CodeObjectUnitUtil.findSegmentByName(this, ".code") != null) {
               for (cq var5 : this.UT.wS()) {
                  int var6 = var5.A.pC();
                  int var7 = var1.write(var5.kS, var6, this.pC, var5.A.kS);
                  if (var7 != var6) {
                     throw new MemoryException("Partial write");
                  }
               }
            }

            if (CodeObjectUnitUtil.findSegmentByName(this, ".globals") != null) {
               for (rQ var10000 : this.E.wS()) {
                  ;
               }
            }

            if (CodeObjectUnitUtil.findSegmentByName(this, ".imports") != null) {
               for (cq var25 : this.UT.UT()) {
                  ;
               }

               for (rQ var19 : this.E.UT()) {
                  if (this.A(var19.ld())) {
                     var1.writeInt(var19.kS, (int)this.A());
                  } else if (this.kS(var19.ld())) {
                     var1.writeInt(var19.kS, (int)this.wS());
                  }
               }
            }

            if (CodeObjectUnitUtil.findSegmentByName(this, ".data") != null) {
               for (Sv var20 : this.fI) {
                  if (var20.pC != 0) {
                     throw new chm();
                  }

                  long var22 = 0L + (var20.A == null ? 0L : var20.A.pC(this));
                  int var8 = var1.write(var22, var20.wS, this.pC, var20.kS);
                  if (var8 != var20.wS) {
                     throw new MemoryException("Partial write");
                  }
               }
            }

            if (CodeObjectUnitUtil.findSegmentByName(this, ".table") != null) {
               for (K var21 : this.oT) {
                  if (var21.pC != 0) {
                     throw new chm();
                  }

                  long var23 = 1073741824L + 4L * var21.A.pC(this);
                  long var24 = var23;

                  for (int var11 : var21.kS) {
                     cq var12 = this.pC(var11);
                     if (var12.E()) {
                        var1.writePointer(var24, var12.kS);
                     }

                     var24 += 4L;
                  }
               }
            }

            return true;
         } catch (MemoryException | chl var13) {
            WR.catchingSilent(var13);
            JebCoreService.notifySilentExceptionToClient(var13);
            return false;
         }
      }
   }

   void pC(byte[] var1, GK var2) {
      vi var3 = new vi(var1, var2.kS);
      int var4 = var3.kS();
      this.wS = new ArrayList(var4);

      for (int var5 = 0; var5 < var4; var5++) {
         DH var6 = var3.E();
         this.wS.add(var6);
         Object[] var10000 = new Object[]{var5, var6};
      }
   }

   void A(byte[] var1, GK var2) {
      vi var3 = new vi(var1, var2.kS);
      int var4 = var3.kS();

      for (int var5 = 0; var5 < var4; var5++) {
         KD var6 = var3.pC(this.wS);
         this.NS.add(var6);
         Object[] var10000 = new Object[]{var5, var6};
         if (var6 instanceof yt) {
            this.vP.add((yt)var6);
            this.UT.pC(new cq((yt)var6));
         } else if (var6 instanceof RC) {
            this.xC.add((RC)var6);
            this.E.pC(new rQ((RC)var6));
         } else if (var6 instanceof sy) {
            this.ED.add((sy)var6);
            this.ys.pC(new oP((sy)var6));
         } else {
            if (!(var6 instanceof HE)) {
               throw new RuntimeException();
            }

            this.Sc.add((HE)var6);
            this.sY.pC(new Mh((HE)var6));
         }
      }
   }

   int kS(byte[] var1, GK var2) {
      vi var3 = new vi(var1, var2.kS);
      int var4 = var3.kS();

      for (int var5 = 0; var5 < var4; var5++) {
         int var6 = var3.kS();
         DH var7 = (DH)this.wS.get(var6);
         cq var8 = new cq(var7, null);
         this.UT.pC(var8);
      }

      return var4;
   }

   void wS(byte[] var1, GK var2) {
      vi var3 = new vi(var1, var2.kS);
      int var4 = var3.kS();

      for (int var5 = 0; var5 < var4; var5++) {
         Mh var6 = var3.fI();
         this.sY.pC(var6);
         Object[] var10000 = new Object[]{var6.wS(), var6};
      }
   }

   void UT(byte[] var1, GK var2) {
      vi var3 = new vi(var1, var2.kS);
      int var4 = var3.kS();

      for (int var5 = 0; var5 < var4; var5++) {
         oP var6 = var3.WR();
         this.ys.pC(var6);
         Object[] var10000 = new Object[]{var6.wS(), var6};
      }
   }

   void E(byte[] var1, GK var2) {
      vi var3 = new vi(var1, var2.kS);
      int var4 = var3.kS();

      for (int var5 = 0; var5 < var4; var5++) {
         rQ var6 = var3.NS();
         this.E.pC(var6);
         Object[] var10000 = new Object[]{var6.wS(), var6};
      }
   }

   void sY(byte[] var1, GK var2) {
      vi var3 = new vi(var1, var2.kS);
      int var4 = var3.kS();

      for (int var5 = 0; var5 < var4; var5++) {
         Ws var6 = var3.vP();
         this.ld.add(var6);
         Object[] var10000 = new Object[]{var5, var6};
      }
   }

   void ys(byte[] var1, GK var2) {
      vi var3 = new vi(var1, var2.kS);
      this.gp = var3.kS();
      Object[] var10000 = new Object[]{this.gp};
   }

   void ld(byte[] var1, GK var2) {
      vi var3 = new vi(var1, var2.kS);
      int var4 = var3.kS();

      for (int var5 = 0; var5 < var4; var5++) {
         K var6 = var3.xC();
         this.oT.add(var6);
         Object[] var10000 = new Object[]{var5, var6};
      }
   }

   void pC(byte[] var1, GK var2, int var3) {
      vi var4 = new vi(var1, var2.kS);
      int var5 = var4.kS();
      if (var5 != var3) {
         throw new RuntimeException(Strings.ff("Unexpected number of code entries: %d (expecting %d)", var5, var3));
      } else {
         for (int var6 = 0; var6 < var5; var6++) {
            cq var7 = (cq)this.UT.pC(this.vP.size() + var6);
            bO var8 = var4.pC(this.A, var7.pC.pC == null);
            var7.A = var8;
            Object[] var10000 = new Object[]{var7.wS(), var7.ld(), var7.pC};
         }
      }
   }

   void gp(byte[] var1, GK var2) {
      vi var3 = new vi(var1, var2.kS);
      int var4 = var3.kS();

      for (int var5 = 0; var5 < var4; var5++) {
         Sv var6 = var3.ED();
         this.fI.add(var6);
         Object[] var10000 = new Object[]{var5, var6, var6.pC(var1)};
      }
   }

   void oT(byte[] var1, GK var2) {
      vi var3 = new vi(var1, var2.kS);
      if (var3.pC() > 0) {
         int var4 = var3.kS();
         Object[] var10000 = new Object[]{var4};
      }
   }

   @Override
   protected boolean shouldAllocateFullImage() {
      return false;
   }

   @Override
   protected boolean applyRelocations(IVirtualMemory var1, long var2, ILinkInfoProvider var4) {
      return false;
   }

   @Override
   public String getDescription() {
      StringBuilder var1 = new StringBuilder(super.getDescription());
      var1.append("\nIndexed Spaces:\n");
      Strings.ff(var1, "- functions : %d (int:%d, imp:%d)\n", this.UT.pC(), this.UT.A(), this.UT.kS());
      Strings.ff(var1, "- globals   : %d (int:%d, imp:%d)\n", this.E.pC(), this.E.A(), this.E.kS());
      Strings.ff(var1, "- memories  : %d (int:%d, imp:%d)\n", this.ys.pC(), this.ys.A(), this.ys.kS());
      Strings.ff(var1, "- tables    : %d (int:%d, imp:%d)\n", this.sY.pC(), this.sY.A(), this.sY.kS());
      return var1.toString();
   }

   public cq pC(int var1) {
      return (cq)this.UT.pC(var1);
   }

   public rQ A(int var1) {
      return (rQ)this.E.pC(var1);
   }

   Long pC(String var1) {
      if (this.A(var1)) {
         return this.A();
      } else {
         return this.kS(var1) ? this.wS() : null;
      }
   }

   public boolean A(String var1) {
      String var2 = var1.toLowerCase();
      return var2.contains("memory") && var2.contains("base");
   }

   public long A() {
      return 0L;
   }

   public long kS() {
      return 1073741824L;
   }

   public boolean kS(String var1) {
      String var2 = var1.toLowerCase();
      return var2.contains("table") && var2.contains("base");
   }

   public long wS() {
      return 0L;
   }

   public cq pC(long var1) {
      for (cq var4 : this.UT) {
         if (var4.E() && var4.kS == var1) {
            return var4;
         }
      }

      return null;
   }

   public cq wS(String var1) {
      for (cq var3 : this.UT) {
         if (var1.equals(var3.ys())) {
            return var3;
         }
      }

      return null;
   }

   public DH kS(int var1) {
      return (DH)this.wS.get(var1);
   }

   private void UT() {
      for (Ws var2 : this.ld) {
         Object var3 = switch (var2.A) {
            case 0 -> this.UT;
            case 1 -> this.sY;
            case 2 -> this.ys;
            case 3 -> this.E;
            default -> throw new RuntimeException();
         };
         if (var2.kS < ((chq)var3).pC()) {
            ((chq)var3).pC(var2.kS).pC(var2.pC);
         }
      }
   }
}
