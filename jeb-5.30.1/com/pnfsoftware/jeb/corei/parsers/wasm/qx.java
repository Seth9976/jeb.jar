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
import com.pnfsoftware.jebglobal.cop;
import com.pnfsoftware.jebglobal.coq;
import com.pnfsoftware.jebglobal.cor;
import com.pnfsoftware.jebglobal.cos;
import com.pnfsoftware.jebglobal.cot;
import com.pnfsoftware.jebglobal.cou;
import com.pnfsoftware.jebglobal.coy;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

@Ser
public class qx extends AbstractCodeObjectUnit {
   private static final ILogger JY = GlobalLog.getLogger(qx.class);
   @SerId(1)
   byte[] q;
   @SerId(2)
   vX RF;
   @SerId(3)
   List xK;
   @SerId(4)
   List Dw = new ArrayList();
   @SerId(5)
   private List HF = new ArrayList();
   @SerId(6)
   private List LK = new ArrayList();
   @SerId(7)
   private List io = new ArrayList();
   @SerId(8)
   private List qa = new ArrayList();
   @SerId(9)
   private List Hk = new ArrayList();
   @SerId(10)
   cor Uv = new cor();
   @SerId(11)
   cos oW = new cos();
   @SerId(12)
   coy gO = new coy();
   @SerId(13)
   cot nf = new cot();
   @SerId(14)
   List gP = new ArrayList();
   @SerId(15)
   Integer za;
   @SerId(16)
   List lm = new ArrayList();
   @SerId(17)
   List zz = new ArrayList();
   private static final int Me = 4;
   private static final long PV = 0L;
   private static final long oQ = 0L;
   private static final long xW = 1073741824L;
   private static final long KT = 1342177280L;
   private static final long Gf = 1610612736L;
   private static final long Ef = 1879048192L;
   private static final String cC = ".data";
   private static final String sH = ".table";
   private static final String CE = ".code";
   private static final String wF = ".globals";
   private static final String If = ".imports";

   public qx(String var1, IInput var2, IUnitProcessor var3, IUnitCreator var4, IPropertyDefinitionManager var5) {
      super(var2, "wasm", var1, var3, var4, var5);
   }

   public vX q() {
      return this.RF;
   }

   @Override
   protected boolean processInternal() {
      try {
         boolean var67;
         try (InputStream var1 = this.getInput().getStream()) {
            this.q = IO.readInputStream(var1);
            int var2 = this.q.length;
            this.RF = new vX(this);
            int var3 = 8;
            this.xK = new ArrayList();

            for (int var4 = 0; var3 < var2; var4++) {
               int var5 = kY.q(this.q, var3);
               kY.eo var6 = kY.gP(this.q, ++var3);
               int var7 = (int)var6.q;
               var3 += var6.RF;
               String var8 = null;
               if (var5 == 0) {
                  var6 = kY.gP(this.q, var3);
                  int var9 = (int)var6.q;
                  var3 += var6.RF;
                  var7 = var7 - var6.RF - var9;
                  var8 = new String(this.q, var3, var9, Charset.forName("UTF-8"));
                  var3 += var9;
               }

               Nz var34 = new Nz(var5, var8, var3, var7);
               this.xK.add(var34);
               Object[] var10000 = new Object[]{var4, var34};
               var3 += var7;
            }

            int var27 = 0;

            for (Nz var30 : this.xK) {
               String var31;
               if (var30.q == 0) {
                  this.logInfo(true, "Skipping processing of custom section: %s", var30.RF);
                  var31 = Strings.safe(var30.RF);
               } else {
                  switch (var30.q) {
                     case 1:
                        this.q(this.q, var30);
                        break;
                     case 2:
                        this.RF(this.q, var30);
                        break;
                     case 3:
                        var27 = this.xK(this.q, var30);
                        this.JY();
                        break;
                     case 4:
                        this.Dw(this.q, var30);
                        this.JY();
                        break;
                     case 5:
                        this.Uv(this.q, var30);
                        this.JY();
                        break;
                     case 6:
                        this.oW(this.q, var30);
                        this.JY();
                        break;
                     case 7:
                        this.gO(this.q, var30);
                        this.JY();
                        break;
                     case 8:
                        this.nf(this.q, var30);
                        break;
                     case 9:
                        this.gP(this.q, var30);
                        break;
                     case 10:
                        this.q(this.q, var30, var27);
                        break;
                     case 11:
                        this.za(this.q, var30);
                        break;
                     case 12:
                        this.lm(this.q, var30);
                        break;
                     default:
                        throw new RuntimeException("Unprocessed section: id=" + var30.q);
                  }

                  var31 = Xa.q(var30.q);
               }

               this.addSection(new SegmentInformation(var31, var30.xK, var30.Dw, 0L, 0L, 2));
            }

            if (this.nf.RF() >= 1) {
               if (this.nf.RF() >= 2) {
                  throw new coq("2+ memories is not supported");
               }

               Bu var72 = (Bu)this.nf.q(0);
            }

            if (this.gO.RF() >= 1) {
               if (this.gO.RF() >= 2) {
                  throw new coq("2+ tables is not supported");
               }

               Uz var73 = (Uz)this.gO.q(0);
            }

            long var32 = 1L;

            for (CU var11 : this.zz) {
               try {
                  long var12 = 0L + (var11.RF == null ? 0L : var11.RF.q(this));
                  long var14 = var12 + var11.Dw;
                  if (var14 > var32) {
                     var32 = var14;
                  }
               } catch (cop var21) {
                  JY.catchingSilent(var21);
               }
            }

            int var35 = (int)(var32 - 0L);
            if (var35 > 0) {
               this.addSegment(new SegmentInformation(".data", 0L, 0L, 0L, var35, 7));
            }

            var32 = 1L;

            for (nI var45 : this.lm) {
               if (var45.q != 0) {
                  throw new coq();
               }

               long var13 = 1073741824L + 4L * var45.RF.q(this);

               for (int var16 : var45.xK) {
                  Nt var17 = this.RF(var16);
                  SymbolInformation var18 = new SymbolInformation(SymbolType.PTRFUNCTION, var17.nf() ? 1 : 0, 0L, var17.zz(), 0L, var13, 4L);
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

            for (Nt var51 : this.Uv.oW()) {
               var51.xK = var41;
               var51.Dw = var51.RF.q();
               this.addSymbol(new SymbolInformation(SymbolType.FUNCTION, 4, var51.gO(), var51.zz(), 0L, var51.xK, var51.Dw));
               var41 += var51.Dw;
            }

            var35 = (int)(var41 - 1342177280L);
            if (var35 > 0) {
               this.addSegment(new SegmentInformation(".code", 0L, 0L, 1342177280L, var35, 7));
            }

            var41 = 1610612736L;

            for (tw var52 : this.oW.oW()) {
               String var59 = var52.zz();
               if (!var52.lm()) {
                  Integer var68 = var52.q();
                  if (var68 != null) {
                     tw var71 = (tw)this.oW.q(var68);
                     if (var71 != null && var71.lm()) {
                        var59 = "__" + var71.JY();
                     }
                  }
               }

               var52.xK = var41;
               var52.Dw = Xa.gO(var52.q.q);
               SymbolInformation var69 = new SymbolInformation(SymbolType.VARIABLE, 0, var52.gO(), var59, 0L, var52.xK, var52.Dw);
               var69.setSymbolDataTypeInformation(Xa.nf(var52.q.q));
               this.addSymbol(var69);
               var41 += var52.Dw;
            }

            var35 = (int)(var41 - 1610612736L);
            if (var35 > 0) {
               this.addSegment(new SegmentInformation(".globals", 0L, 0L, 1610612736L, var35, 7));
            }

            var41 = 1879048192L;

            for (tw var53 : this.oW.gO()) {
               var53.xK = var41;
               SymbolInformation var60 = new SymbolInformation(SymbolType.PTRVARIABLE, 1, var53.gO(), var53.zz(), 0L, var41, 4L);
               this.addSymbol(var60);
               var41 += 4L;
            }

            var41 = var41 + 15L & -16L;

            for (Nt var54 : this.Uv.gO()) {
               var54.xK = var41;
               SymbolInformation var61 = new SymbolInformation(SymbolType.PTRFUNCTION, 1, var54.gO(), var54.zz(), 0L, var41, 4L);
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
            var74 = new Object[]{this.Uv.RF(), this.Uv.xK(), this.Uv.Dw()};
            var74 = new Object[]{this.oW.RF(), this.oW.xK(), this.oW.Dw()};
            var74 = new Object[]{this.nf.RF(), this.nf.xK(), this.nf.Dw()};
            var74 = new Object[]{this.gO.RF(), this.gO.xK(), this.gO.Dw()};
            var74 = new Object[0];

            for (tw var62 : this.oW) {
               var74 = new Object[]{var62};
            }

            var74 = new Object[0];

            for (Uz var63 : this.gO) {
               var74 = new Object[]{var63};
            }

            var74 = new Object[0];

            for (Bu var64 : this.nf) {
               var74 = new Object[]{var64};
            }

            var74 = new Object[0];

            for (Nt var65 : this.Uv.oW()) {
               var74 = new Object[]{var65};
               var65.oW();
               var74 = new Object[0];
            }

            try {
               String var66 = "wasmbc";
               IUnit var70 = this.getUnitProcessor().process(var66 + " image", this.getInput(), this, var66, true);
               if (var70 != null) {
                  this.addChild(var70);
               }
            } catch (Exception var20) {
               JY.catching(var20);
               this.addNotification(new UnitNotification(NotificationType.UNSUPPORTED_FEATURE, S.L("The bytecode was not disassembled")));
            }

            var67 = true;
         }

         return var67;
      } catch (Exception var23) {
         JY.catching(var23);
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
               for (Nt var5 : this.Uv.oW()) {
                  int var6 = var5.RF.q();
                  int var7 = var1.write(var5.xK, var6, this.q, var5.RF.xK);
                  if (var7 != var6) {
                     throw new MemoryException("Partial write");
                  }
               }
            }

            if (CodeObjectUnitUtil.findSegmentByName(this, ".globals") != null) {
               for (tw var10000 : this.oW.oW()) {
                  ;
               }
            }

            if (CodeObjectUnitUtil.findSegmentByName(this, ".imports") != null) {
               for (Nt var25 : this.Uv.gO()) {
                  ;
               }

               for (tw var19 : this.oW.gO()) {
                  if (this.RF(var19.JY())) {
                     var1.writeInt(var19.xK, (int)this.oW());
                  } else if (this.xK(var19.JY())) {
                     var1.writeInt(var19.xK, (int)this.gP());
                  }
               }
            }

            if (CodeObjectUnitUtil.findSegmentByName(this, ".data") != null) {
               for (CU var20 : this.zz) {
                  if (var20.q != 0) {
                     throw new coq();
                  }

                  long var22 = 0L + (var20.RF == null ? 0L : var20.RF.q(this));
                  int var8 = var1.write(var22, var20.Dw, this.q, var20.xK);
                  if (var8 != var20.Dw) {
                     throw new MemoryException("Partial write");
                  }
               }
            }

            if (CodeObjectUnitUtil.findSegmentByName(this, ".table") != null) {
               for (nI var21 : this.lm) {
                  if (var21.q != 0) {
                     throw new coq();
                  }

                  long var23 = 1073741824L + 4L * var21.RF.q(this);
                  long var24 = var23;

                  for (int var11 : var21.xK) {
                     Nt var12 = this.RF(var11);
                     if (var12.gP()) {
                        var1.writePointer(var24, var12.xK);
                     }

                     var24 += 4L;
                  }
               }
            }

            return true;
         } catch (MemoryException | cop var13) {
            JY.catchingSilent(var13);
            JebCoreService.notifySilentExceptionToClient(var13);
            return false;
         }
      }
   }

   void q(byte[] var1, Nz var2) {
      bK var3 = new bK(var1, var2.xK);
      int var4 = var3.xK();
      this.Dw = new ArrayList(var4);

      for (int var5 = 0; var5 < var4; var5++) {
         iZ var6 = var3.gO();
         this.Dw.add(var6);
         Object[] var10000 = new Object[]{var5, var6};
      }
   }

   void RF(byte[] var1, Nz var2) {
      bK var3 = new bK(var1, var2.xK);
      int var4 = var3.xK();

      for (int var5 = 0; var5 < var4; var5++) {
         qV var6 = var3.q(this.Dw);
         this.HF.add(var6);
         Object[] var10000 = new Object[]{var5, var6};
         if (var6 instanceof vn) {
            this.LK.add((vn)var6);
            this.Uv.q(new Nt((vn)var6));
         } else if (var6 instanceof PY) {
            this.io.add((PY)var6);
            this.oW.q(new tw((PY)var6));
         } else if (var6 instanceof vb) {
            this.qa.add((vb)var6);
            this.nf.q(new Bu((vb)var6));
         } else {
            if (!(var6 instanceof oL)) {
               throw new RuntimeException();
            }

            this.Hk.add((oL)var6);
            this.gO.q(new Uz((oL)var6));
         }
      }
   }

   int xK(byte[] var1, Nz var2) {
      bK var3 = new bK(var1, var2.xK);
      int var4 = var3.xK();

      for (int var5 = 0; var5 < var4; var5++) {
         int var6 = var3.xK();
         iZ var7 = (iZ)this.Dw.get(var6);
         Nt var8 = new Nt(var7, null);
         this.Uv.q(var8);
      }

      return var4;
   }

   void Dw(byte[] var1, Nz var2) {
      bK var3 = new bK(var1, var2.xK);
      int var4 = var3.xK();

      for (int var5 = 0; var5 < var4; var5++) {
         Uz var6 = var3.JY();
         this.gO.q(var6);
         Object[] var10000 = new Object[]{var6.gO(), var6};
      }
   }

   void Uv(byte[] var1, Nz var2) {
      bK var3 = new bK(var1, var2.xK);
      int var4 = var3.xK();

      for (int var5 = 0; var5 < var4; var5++) {
         Bu var6 = var3.HF();
         this.nf.q(var6);
         Object[] var10000 = new Object[]{var6.gO(), var6};
      }
   }

   void oW(byte[] var1, Nz var2) {
      bK var3 = new bK(var1, var2.xK);
      int var4 = var3.xK();

      for (int var5 = 0; var5 < var4; var5++) {
         tw var6 = var3.LK();
         this.oW.q(var6);
         Object[] var10000 = new Object[]{var6.gO(), var6};
      }
   }

   void gO(byte[] var1, Nz var2) {
      bK var3 = new bK(var1, var2.xK);
      int var4 = var3.xK();

      for (int var5 = 0; var5 < var4; var5++) {
         ej var6 = var3.io();
         this.gP.add(var6);
         Object[] var10000 = new Object[]{var5, var6};
      }
   }

   void nf(byte[] var1, Nz var2) {
      bK var3 = new bK(var1, var2.xK);
      this.za = var3.xK();
      Object[] var10000 = new Object[]{this.za};
   }

   void gP(byte[] var1, Nz var2) {
      bK var3 = new bK(var1, var2.xK);
      int var4 = var3.xK();

      for (int var5 = 0; var5 < var4; var5++) {
         nI var6 = var3.qa();
         this.lm.add(var6);
         Object[] var10000 = new Object[]{var5, var6};
      }
   }

   void q(byte[] var1, Nz var2, int var3) {
      bK var4 = new bK(var1, var2.xK);
      int var5 = var4.xK();
      if (var5 != var3) {
         throw new RuntimeException(Strings.ff("Unexpected number of code entries: %d (expecting %d)", var5, var3));
      } else {
         for (int var6 = 0; var6 < var5; var6++) {
            Nt var7 = (Nt)this.Uv.q(this.LK.size() + var6);
            oM var8 = var4.q(this.RF, var7.q.q == null);
            var7.RF = var8;
            Object[] var10000 = new Object[]{var7.gO(), var7.JY(), var7.q};
         }
      }
   }

   void za(byte[] var1, Nz var2) {
      bK var3 = new bK(var1, var2.xK);
      int var4 = var3.xK();

      for (int var5 = 0; var5 < var4; var5++) {
         CU var6 = var3.Hk();
         this.zz.add(var6);
         Object[] var10000 = new Object[]{var5, var6, var6.q(var1)};
      }
   }

   void lm(byte[] var1, Nz var2) {
      bK var3 = new bK(var1, var2.xK);
      if (var3.q() > 0) {
         int var4 = var3.xK();
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
      Strings.ff(var1, "- functions : %d (int:%d, imp:%d)\n", this.Uv.RF(), this.Uv.xK(), this.Uv.Dw());
      Strings.ff(var1, "- globals   : %d (int:%d, imp:%d)\n", this.oW.RF(), this.oW.xK(), this.oW.Dw());
      Strings.ff(var1, "- memories  : %d (int:%d, imp:%d)\n", this.nf.RF(), this.nf.xK(), this.nf.Dw());
      Strings.ff(var1, "- tables    : %d (int:%d, imp:%d)\n", this.gO.RF(), this.gO.xK(), this.gO.Dw());
      return var1.toString();
   }

   public int RF() {
      return this.Uv.RF();
   }

   public int xK() {
      return this.nf.RF();
   }

   public int Dw() {
      return this.gO.RF();
   }

   public int Uv() {
      return this.oW.RF();
   }

   public String q(int var1) {
      return Strings.safe(((Nt)this.Uv.q(var1)).zz(), "f" + var1);
   }

   public Nt RF(int var1) {
      return (Nt)this.Uv.q(var1);
   }

   public tw xK(int var1) {
      return (tw)this.oW.q(var1);
   }

   Long q(String var1) {
      if (this.RF(var1)) {
         return this.oW();
      } else {
         return this.xK(var1) ? this.gP() : null;
      }
   }

   public boolean RF(String var1) {
      String var2 = var1.toLowerCase();
      return var2.contains("memory") && var2.contains("base");
   }

   public long oW() {
      return 0L;
   }

   public long gO() {
      return 0L;
   }

   public long nf() {
      return 1073741824L;
   }

   public boolean xK(String var1) {
      String var2 = var1.toLowerCase();
      return var2.contains("table") && var2.contains("base");
   }

   public long gP() {
      return 0L;
   }

   public long za() {
      return 1610612736L;
   }

   public long lm() {
      return 1879048192L;
   }

   public long zz() {
      return 1342177280L;
   }

   public Bu Dw(int var1) {
      return (Bu)this.nf.q(var1);
   }

   public Uz Uv(int var1) {
      return (Uz)this.gO.q(var1);
   }

   public Nt q(long var1) {
      for (Nt var4 : this.Uv) {
         if (var4.gP() && var4.xK == var1) {
            return var4;
         }
      }

      return null;
   }

   public Nt Dw(String var1) {
      for (Nt var3 : this.Uv) {
         if (var1.equals(var3.zz())) {
            return var3;
         }
      }

      return null;
   }

   public iZ oW(int var1) {
      return (iZ)this.Dw.get(var1);
   }

   private void JY() {
      for (ej var2 : this.gP) {
         Object var3 = switch (var2.RF) {
            case 0 -> this.Uv;
            case 1 -> this.gO;
            case 2 -> this.nf;
            case 3 -> this.oW;
            default -> throw new RuntimeException();
         };
         if (var2.xK < ((cou)var3).RF()) {
            ((cou)var3).q(var2.xK).q(var2.q);
         }
      }
   }
}
