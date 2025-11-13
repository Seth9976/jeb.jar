package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.core.JebCoreService;
import com.pnfsoftware.jeb.core.properties.impl.PropertyUtil;
import com.pnfsoftware.jeb.core.units.INativeCodeUnit;
import com.pnfsoftware.jeb.core.units.UnitUtil;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.CodeGapAnalysisStyle;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.DataGapAnalysisStyle;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.INativeCodeAnalyzer;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.INativeCodeAnalyzerExtension;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.MemoryRanges;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.NativeAnalyzerException;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeContinuousItem;
import com.pnfsoftware.jeb.core.units.codeobject.CodeObjectUnitUtil;
import com.pnfsoftware.jeb.core.units.codeobject.ICodeObjectUnit;
import com.pnfsoftware.jeb.core.units.codeobject.IELFHeader;
import com.pnfsoftware.jeb.core.units.codeobject.IELFUnit;
import com.pnfsoftware.jeb.core.units.codeobject.ISegmentInformation;
import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.collect.MultiMap;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import com.pnfsoftware.jeb.util.serialization.annotations.SerTransient;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Ser
public class Vr {
   private static final ILogger wS = GlobalLog.getLogger(Vr.class);
   @SerId(1)
   private INativeCodeUnit UT;
   @SerId(2)
   private ICodeObjectUnit E;
   @SerId(3)
   private int sY;
   @SerId(4)
   private Set ys;
   @SerId(5)
   private boolean ld;
   @SerId(6)
   private MemoryRanges gp;
   @SerId(7)
   private BL oT;
   @SerId(8)
   private DataGapAnalysisStyle fI;
   @SerId(9)
   private CodeGapAnalysisStyle WR;
   @SerId(10)
   private Set NS;
   @SerTransient
   private boolean vP;
   @SerTransient
   private Set xC;
   @SerTransient
   List pC;
   @SerTransient
   List A;
   @SerTransient
   List kS;
   @SerTransient
   private int ED = 0;
   @SerTransient
   private List Sc;
   @SerTransient
   private boolean ah = false;

   public Vr(INativeCodeUnit var1) {
      this.ys = new HashSet();
      this.UT = var1;
      this.E = var1.getCodeObjectContainer();
      if (this.E != null) {
         this.pC(this.E);
      }

      this.pC();
   }

   public void pC() {
      MultiMap var1 = bK.pC(this.UT);
      this.oT = new BL(var1);
      this.WR = this.z();
      this.fI = this.rl();
      ((a)this.UT.getCodeAnalyzer()).A = this.ah();
   }

   private void pC(ICodeObjectUnit var1) {
      if (var1 instanceof IELFUnit && this.UT.getVirtualImageBase() == 0L) {
         if (this.gp == null) {
            this.gp = new MemoryRanges(this.UT.getMemory().getSpaceBits());
         }

         IELFHeader var2 = ((IELFUnit)var1).getHeader();
         long var3 = var2.getHeaderSize() & 4294967295L;
         if (var2.getProgramHeaderTableOffset() != 0L) {
            var3 = var2.getProgramHeaderTableOffset() + (var2.getProgramHeaderTableEntryNumber() * var2.getProgramHeaderTableEntrySize() & 4294967295L);
         }

         if (var3 < 20480L) {
            this.gp.add(0L, var3);
         }

         if (var2.getType() == 1) {
            for (ISegmentInformation var6 : var1.getSections()) {
               if (var6.getName().startsWith(".rel.") || var6.getName().startsWith(".rela.") || var6.getName().equals(".symtab")) {
                  this.gp.add(var6.getOffsetInMemory(), var6.getOffsetInMemory() + var6.getSizeInMemory());
               }
            }
         }
      }

      if (var1 instanceof com.pnfsoftware.jeb.corei.parsers.macho.zp && this.UT.getVirtualImageBase() == 0L) {
         if (this.gp == null) {
            this.gp = new MemoryRanges(this.UT.getMemory().getSpaceBits());
         }

         ISegmentInformation var7 = CodeObjectUnitUtil.findSectionByName(var1, "<hdr>");
         if (var7 != null && var7.getSizeInMemory() < 20480L) {
            this.gp.add(0L, var7.getSizeInMemory());
         }
      }
   }

   public INativeCodeUnit A() {
      return this.UT;
   }

   public boolean kS() {
      return this.oT.wS();
   }

   public boolean wS() {
      return this.oT.UT();
   }

   public List UT() {
      List var1 = this.oT.pC();
      INativeCodeAnalyzerExtension var2 = this.UT.getAnalyzerExtension();
      if (var2 != null) {
         var1.add(var2);
      }

      return var1;
   }

   public List E() {
      Assert.a(this.UT.getCodeAnalyzer() != null);
      INativeCodeAnalyzer var1 = this.UT.getCodeAnalyzer();
      this.A = new ArrayList();
      this.A.add(new ud((a)var1));
      return this.A;
   }

   // $VF: Unable to simplify switch on enum
   // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
   public List sY() {
      Assert.a(this.UT.getCodeAnalyzer() != null);
      INativeCodeAnalyzer var1 = this.UT.getCodeAnalyzer();
      this.pC = new ArrayList();
      switch (this.fI) {
         case DEFAULT:
            this.pC.add(new nm((a)var1));
            this.pC.add(new GV((a)var1));
            break;
         case NONE:
            this.pC.add(new Qq((a)var1));
      }

      return this.pC;
   }

   private DataGapAnalysisStyle rl() {
      DataGapAnalysisStyle var1 = this.Aj();
      if (var1 == null) {
         var1 = this.oT.A();
      }

      return var1;
   }

   // $VF: Unable to simplify switch on enum
   // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
   public List ys() {
      Assert.a(this.UT.getCodeAnalyzer() != null);
      INativeCodeAnalyzer var1 = this.UT.getCodeAnalyzer();
      this.kS = new ArrayList();
      switch (this.WR) {
         case PROLOGUES_ONLY:
            this.kS.add(new Dz((a)var1));
            break;
         case LINEAR_SWEEP:
            this.kS.add(new Wf((a)var1));
            break;
         case NONE:
            this.kS.add(new iC((a)var1));
            break;
         default:
            return null;
      }

      for (kx var3 : this.kS) {
         this.pC(var3);
      }

      return this.kS;
   }

   private CodeGapAnalysisStyle z() {
      CodeGapAnalysisStyle var1 = this.FE();
      if (var1 == null) {
         var1 = this.oT.kS();
      }

      return var1;
   }

   private void pC(Sj var1) {
      if (this.Sc == null) {
         this.Sc = new ArrayList();
      }

      this.Sc.add(var1);
   }

   private void A(Sj var1) {
      if (this.Sc != null) {
         this.Sc.remove(var1);
      }
   }

   public iL.Av ld() {
      int var1 = this.hK();
      iL.Av var2 = (iL.Av)PropertyUtil.convertSelectionId(var1, iL.Av.class);
      return var2 == null ? this.oT.pC(this.UT) : var2;
   }

   public com.pnfsoftware.jeb.corei.parsers.x86.wn.Av gp() {
      int var1 = this.Er();
      com.pnfsoftware.jeb.corei.parsers.x86.wn.Av var2 = (com.pnfsoftware.jeb.corei.parsers.x86.wn.Av)PropertyUtil.convertSelectionId(
         var1, com.pnfsoftware.jeb.corei.parsers.x86.wn.Av.class
      );
      return var2 == null ? this.oT.A(this.UT) : var2;
   }

   public static int pC(CodeGapAnalysisStyle var0) {
      return pC(var0, 0);
   }

   // $VF: Unable to simplify switch on enum
   // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
   private static int pC(CodeGapAnalysisStyle var0, int var1) {
      switch (var0) {
         case PROLOGUES_ONLY:
         case NONE:
            return 1;
         case LINEAR_SWEEP:
         default:
            return 0;
      }
   }

   public int oT() {
      return pC(this.WR, this.ED);
   }

   public int fI() {
      return this.oT.E();
   }

   // $VF: Unable to simplify switch on enum
   // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
   public boolean pC(int var1) {
      if (var1 >= 1) {
         return false;
      } else {
         switch (this.WR) {
            case PROLOGUES_ONLY:
            case NONE:
               return false;
            case LINEAR_SWEEP:
            default:
               return true;
         }
      }
   }

   private int Ek() {
      return this.UT.getPropertyManager().getInteger("AnalysisStyle");
   }

   private int hK() {
      return this.UT.getPropertyManager().getInteger("TailCallAnalysisStyle");
   }

   private int Er() {
      return this.UT.getPropertyManager().getInteger("SwitchAnalysisStyle");
   }

   protected int WR() {
      return this.UT.getPropertyManager().getInteger("SignaturePackagesLoading");
   }

   public long NS() {
      return this.UT.getVirtualImageBase();
   }

   public long vP() {
      return this.UT.getImageSize();
   }

   private CodeGapAnalysisStyle FE() {
      if (!this.vP) {
         int var1 = this.Ek();
         switch (var1) {
            case 0:
               return CodeGapAnalysisStyle.PROLOGUES_ONLY;
            case 1:
            default:
               break;
            case 2:
               return CodeGapAnalysisStyle.LINEAR_SWEEP;
            case 3:
            case 4:
               return CodeGapAnalysisStyle.NONE;
         }
      }

      return null;
   }

   private DataGapAnalysisStyle Aj() {
      if (!this.vP) {
         int var1 = this.Ek();
         switch (var1) {
            case 0:
            case 2:
            case 3:
               return DataGapAnalysisStyle.DEFAULT;
            case 1:
            default:
               break;
            case 4:
               return DataGapAnalysisStyle.NONE;
         }
      }

      return null;
   }

   public boolean xC() {
      return this.oT.sY();
   }

   // $VF: Unable to simplify switch on enum
   // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
   public void pC(Vr.Av var1, Object var2) {
      this.UT.getLock().verifyLocked();

      for (Sj var4 : this.Sc) {
         var4.pC(var1, var2);
      }

      if (this.ah) {
         this.EX();
         this.ah = false;
      }

      switch (var1) {
         case A:
            if (var2 instanceof Long) {
               this.ys.add((Long)var2);
               if (this.ys.size() > 50) {
                  this.pC(Strings.ff("max routines parsing fail reached at %xh", var2));
               }
            }
            break;
         case pC:
            if (var2 instanceof Long) {
               this.ys.remove(var2);
            }
            break;
         case wS:
            if (var2 instanceof Long) {
               if (this.xC == null) {
                  this.xC = new HashSet();
               }

               this.xC.add((Long)var2);
            }
            break;
         case kS:
            if (var2 instanceof Long) {
               if (this.xC == null) {
                  this.xC = new HashSet();
               }

               this.xC.remove(var2);
            }
            break;
         case UT:
            if (var2 instanceof Long) {
               if (this.NS == null) {
                  this.NS = new HashSet();
               }

               if (this.UT.getDetectedCompiler().isVisualStudio() && this.UT.getProcessor().getType().isIntel() && !this.NS.contains(var2)) {
                  INativeContinuousItem var6 = this.UT.getCodeModel().getNextItem((Long)var2);
                  if (var6 != null) {
                     for (kx var5 : this.kS) {
                        var5.pC((Long)var2, var6.getMemoryAddress());
                     }

                     this.NS.add((Long)var2);
                  }
               }

               this.pC(Strings.ff("switch construction failed at %xh", var2));
            }
      }
   }

   public void pC(boolean var1) {
      this.ah = var1;
   }

   private void EX() {
      this.UT.getLock().verifyLocked();
      Assert.a(this.UT.getCodeAnalyzer() != null);
      a var1 = (a)this.UT.getCodeAnalyzer();
      if (this.WR == CodeGapAnalysisStyle.LINEAR_SWEEP && this.Ek() == 1) {
         this.WR = CodeGapAnalysisStyle.PROLOGUES_ONLY;
         if (this.kS != null) {
            for (kx var3 : this.kS) {
               this.A(var3);
            }
         }

         ArrayList var7 = new ArrayList(this.kS);
         this.kS = this.ys();

         for (kx var4 : this.kS) {
            for (kx var6 : var7) {
               var4.pC(var6.sY());
            }
         }

         var1.pC(this.kS);
         UnitUtil.logInfo(this.UT, null, true, wS, S.L("Code gap processor downgraded to %s"), CodeGapAnalysisStyle.PROLOGUES_ONLY);
      }

      this.pC("code gap proc downgrade");
   }

   private void pC(String var1) {
      if (!this.ld) {
         this.ld = true;
         JebCoreService.notifySilentExceptionToClient(
            new NativeAnalyzerException(
               Strings.ff(
                  "Unit: %s - container: %s - details: %s",
                  this.UT.getName(),
                  this.UT.getCodeObjectContainer() != null ? this.UT.getCodeObjectContainer().getName() : "unknown",
                  var1 != null ? var1 : "none"
               )
            )
         );
      }
   }

   public int A(boolean var1) {
      return this.oT.ys();
   }

   public int ED() {
      return this.oT.ld();
   }

   public int Sc() {
      return this.oT.gp();
   }

   // $VF: Unable to simplify switch on enum
   // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
   public boolean ah() {
      switch (this.fI) {
         case DEFAULT:
            return true;
         case NONE:
         default:
            return false;
      }
   }

   public MemoryRanges eP() {
      return this.gp;
   }

   public DataGapAnalysisStyle UO() {
      return this.fI;
   }

   public CodeGapAnalysisStyle Ab() {
      return this.WR;
   }

   public void A(int var1) {
      this.ED = var1;
   }

   public static enum Av {
      pC,
      A,
      kS,
      wS,
      UT;
   }
}
