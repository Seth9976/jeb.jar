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
import com.pnfsoftware.jeb.util.base.Couple;
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
public class aaw {
   private static final ILogger Dw = GlobalLog.getLogger(aaw.class);
   private static final int Uv = 50;
   @SerId(1)
   private INativeCodeUnit oW;
   @SerId(2)
   private ICodeObjectUnit gO;
   @SerId(3)
   private int nf;
   @SerId(4)
   private Set gP;
   @SerId(5)
   private boolean za;
   @SerId(6)
   private MemoryRanges lm;
   @SerId(7)
   private aaz zz;
   @SerId(8)
   private DataGapAnalysisStyle JY;
   @SerId(9)
   private CodeGapAnalysisStyle HF;
   @SerId(10)
   private Set LK;
   @SerTransient
   private boolean io;
   @SerTransient
   private Set qa;
   @SerTransient
   List q;
   @SerTransient
   List RF;
   @SerTransient
   List xK;
   @SerTransient
   private int Hk = 0;
   @SerTransient
   private List Me;
   @SerTransient
   private boolean PV = false;

   public aaw(INativeCodeUnit var1) {
      this.gP = new HashSet();
      this.oW = var1;
      this.gO = var1.getCodeObjectContainer();
      if (this.gO != null) {
         this.q(this.gO);
      }

      this.q();
   }

   public void q() {
      MultiMap var1 = aba.q(this.oW);
      this.zz = new aaz(var1);
      this.HF = this.sH();
      this.JY = this.cC();
      ((aae)this.oW.getCodeAnalyzer()).RF = this.oQ();
   }

   private void q(ICodeObjectUnit var1) {
      if (var1 instanceof IELFUnit && this.oW.getVirtualImageBase() == 0L) {
         if (this.lm == null) {
            this.lm = new MemoryRanges(this.oW.getMemory().getSpaceBits());
         }

         IELFHeader var2 = ((IELFUnit)var1).getHeader();
         long var3 = var2.getHeaderSize() & 4294967295L;
         if (var2.getProgramHeaderTableOffset() != 0L) {
            var3 = var2.getProgramHeaderTableOffset() + (var2.getProgramHeaderTableEntryNumber() * var2.getProgramHeaderTableEntrySize() & 4294967295L);
         }

         if (var3 < 20480L) {
            this.lm.add(0L, var3);
         }

         if (var2.getType() == 1) {
            for (ISegmentInformation var6 : var1.getSections()) {
               if (var6.getName().startsWith(".rel.") || var6.getName().startsWith(".rela.") || var6.getName().equals(".symtab")) {
                  this.lm.add(var6.getOffsetInMemory(), var6.getOffsetInMemory() + var6.getSizeInMemory());
               }
            }
         }
      }

      if (var1 instanceof com.pnfsoftware.jeb.corei.parsers.macho.EE && this.oW.getVirtualImageBase() == 0L) {
         if (this.lm == null) {
            this.lm = new MemoryRanges(this.oW.getMemory().getSpaceBits());
         }

         ISegmentInformation var7 = CodeObjectUnitUtil.findSectionByName(var1, "<hdr>");
         if (var7 != null && var7.getSizeInMemory() < 20480L) {
            this.lm.add(0L, var7.getSizeInMemory());
         }
      }
   }

   public Set RF() {
      return this.gP;
   }

   public Set xK() {
      return this.qa;
   }

   public INativeCodeUnit Dw() {
      return this.oW;
   }

   public boolean Uv() {
      return this.zz.Dw();
   }

   public boolean oW() {
      return this.zz.Uv();
   }

   public List gO() {
      List var1 = this.zz.q();
      INativeCodeAnalyzerExtension var2 = this.oW.getAnalyzerExtension();
      if (var2 != null) {
         var1.add(var2);
      }

      return var1;
   }

   public List nf() {
      Assert.a(this.oW.getCodeAnalyzer() != null);
      INativeCodeAnalyzer var1 = this.oW.getCodeAnalyzer();
      this.RF = new ArrayList();
      this.RF.add(new aaa((aae)var1));
      return this.RF;
   }

   public List gP() {
      Assert.a(this.oW.getCodeAnalyzer() != null);
      INativeCodeAnalyzer var1 = this.oW.getCodeAnalyzer();
      this.q = new ArrayList();
      switch (this.JY) {
         case DEFAULT:
            this.q.add(new aac((aae)var1));
            this.q.add(new aab((aae)var1));
            break;
         case NONE:
            this.q.add(new Xk((aae)var1));
      }

      return this.q;
   }

   private DataGapAnalysisStyle cC() {
      DataGapAnalysisStyle var1 = this.mI();
      if (var1 == null) {
         var1 = this.zz.RF();
      }

      return var1;
   }

   public List za() {
      Assert.a(this.oW.getCodeAnalyzer() != null);
      INativeCodeAnalyzer var1 = this.oW.getCodeAnalyzer();
      this.xK = new ArrayList();
      switch (this.HF) {
         case PROLOGUES_ONLY:
            this.xK.add(new Rw((aae)var1));
            break;
         case LINEAR_SWEEP:
            this.xK.add(new i((aae)var1));
            break;
         case NONE:
            this.xK.add(new hN((aae)var1));
            break;
         default:
            return null;
      }

      for (PA var3 : this.xK) {
         this.q(var3);
      }

      return this.xK;
   }

   private CodeGapAnalysisStyle sH() {
      CodeGapAnalysisStyle var1 = this.Dz();
      if (var1 == null) {
         var1 = this.zz.xK();
      }

      return var1;
   }

   private void q(aan var1) {
      if (this.Me == null) {
         this.Me = new ArrayList();
      }

      this.Me.add(var1);
   }

   private void RF(aan var1) {
      if (this.Me != null) {
         this.Me.remove(var1);
      }
   }

   public aap.eo lm() {
      int var1 = this.wF();
      aap.eo var2 = (aap.eo)PropertyUtil.convertSelectionId(var1, aap.eo.class);
      return var2 == null ? this.zz.q(this.oW) : var2;
   }

   public ctk.eo zz() {
      int var1 = this.If();
      ctk.eo var2 = (ctk.eo)PropertyUtil.convertSelectionId(var1, ctk.eo.class);
      return var2 == null ? this.zz.RF(this.oW) : var2;
   }

   public static int q(CodeGapAnalysisStyle var0) {
      return q(var0, 0);
   }

   private static int q(CodeGapAnalysisStyle var0, int var1) {
      switch (var0) {
         case PROLOGUES_ONLY:
         case NONE:
            return 1;
         case LINEAR_SWEEP:
         default:
            return 0;
      }
   }

   public int JY() {
      return q(this.HF, this.Hk);
   }

   public int HF() {
      return this.zz.oW();
   }

   public boolean q(int var1) {
      if (var1 >= 1) {
         return false;
      } else {
         switch (this.HF) {
            case PROLOGUES_ONLY:
            case NONE:
               return false;
            case LINEAR_SWEEP:
            default:
               return true;
         }
      }
   }

   public void q(boolean var1) {
      this.io = var1;
   }

   private int CE() {
      return this.oW.getPropertyManager().getInteger("AnalysisStyle");
   }

   private int wF() {
      return this.oW.getPropertyManager().getInteger("TailCallAnalysisStyle");
   }

   private int If() {
      return this.oW.getPropertyManager().getInteger("SwitchAnalysisStyle");
   }

   protected int LK() {
      return this.oW.getPropertyManager().getInteger("SignaturePackagesLoading");
   }

   public long io() {
      return this.oW.getVirtualImageBase();
   }

   public long qa() {
      return this.oW.getImageSize();
   }

   private CodeGapAnalysisStyle Dz() {
      if (!this.io) {
         int var1 = this.CE();
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

   private DataGapAnalysisStyle mI() {
      if (!this.io) {
         int var1 = this.CE();
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

   public boolean Hk() {
      return this.zz.gO();
   }

   public void q(aaw.eo var1, Object var2) {
      this.oW.getLock().verifyLocked();

      for (aan var4 : this.Me) {
         var4.q(var1, var2);
      }

      if (this.PV) {
         this.jq();
         this.PV = false;
      }

      switch (var1) {
         case RF:
            if (var2 instanceof Long) {
               this.gP.add((Long)var2);
               if (this.gP.size() > 50) {
                  this.q(Strings.ff("max routines parsing fail reached at %xh", var2));
               }
            }
            break;
         case q:
            if (var2 instanceof Long) {
               this.gP.remove(var2);
            }
            break;
         case Dw:
            if (var2 instanceof Long) {
               if (this.qa == null) {
                  this.qa = new HashSet();
               }

               this.qa.add((Long)var2);
            }
            break;
         case xK:
            if (var2 instanceof Long) {
               if (this.qa == null) {
                  this.qa = new HashSet();
               }

               this.qa.remove(var2);
            }
            break;
         case Uv:
            if (var2 instanceof Long) {
               if (this.LK == null) {
                  this.LK = new HashSet();
               }

               if (this.oW.getDetectedCompiler().isVisualStudio() && this.oW.getProcessor().getType().isIntel() && !this.LK.contains(var2)) {
                  INativeContinuousItem var6 = this.oW.getCodeModel().getNextItem((Long)var2);
                  if (var6 != null) {
                     for (PA var5 : this.xK) {
                        var5.q((Long)var2, var6.getMemoryAddress());
                     }

                     this.LK.add((Long)var2);
                  }
               }

               this.q(Strings.ff("switch construction failed at %xh", var2));
            }
      }
   }

   public void RF(boolean var1) {
      this.PV = var1;
   }

   private void jq() {
      this.oW.getLock().verifyLocked();
      Assert.a(this.oW.getCodeAnalyzer() != null);
      aae var1 = (aae)this.oW.getCodeAnalyzer();
      if (this.HF == CodeGapAnalysisStyle.LINEAR_SWEEP && this.CE() == 1) {
         this.HF = CodeGapAnalysisStyle.PROLOGUES_ONLY;
         if (this.xK != null) {
            for (PA var3 : this.xK) {
               this.RF(var3);
            }
         }

         ArrayList var7 = new ArrayList(this.xK);
         this.xK = this.za();

         for (PA var4 : this.xK) {
            for (PA var6 : var7) {
               var4.q(var6.gO());
            }
         }

         var1.q(this.xK);
         UnitUtil.logInfo(this.oW, null, true, Dw, S.L("Code gap processor downgraded to %s"), CodeGapAnalysisStyle.PROLOGUES_ONLY);
      }

      this.q("code gap proc downgrade");
   }

   private void q(String var1) {
      if (!this.za) {
         this.za = true;
         JebCoreService.notifySilentExceptionToClient(
            new NativeAnalyzerException(
               Strings.ff(
                  "Unit: %s - container: %s - details: %s",
                  this.oW.getName(),
                  this.oW.getCodeObjectContainer() != null ? this.oW.getCodeObjectContainer().getName() : "unknown",
                  var1 != null ? var1 : "none"
               )
            )
         );
      }
   }

   public int xK(boolean var1) {
      return this.zz.nf();
   }

   public int Me() {
      return this.zz.gP();
   }

   public int PV() {
      return this.zz.za();
   }

   public boolean oQ() {
      switch (this.JY) {
         case DEFAULT:
            return true;
         case NONE:
         default:
            return false;
      }
   }

   public List xW() {
      this.oW.getLock().verifyLocked();
      ArrayList var1 = new ArrayList();

      for (Long var3 : this.gP) {
         Couple var4 = new Couple(var3, "unresolved routine parsing failure");
         var1.add(var4);
      }

      if (this.qa != null) {
         for (Long var6 : this.qa) {
            Couple var7 = new Couple(var6, "instruction parsing fail");
            var1.add(var7);
         }
      }

      return var1;
   }

   public MemoryRanges KT() {
      return this.lm;
   }

   public void q(DataGapAnalysisStyle var1) {
      this.JY = var1;
   }

   public DataGapAnalysisStyle Gf() {
      return this.JY;
   }

   public void RF(CodeGapAnalysisStyle var1) {
      this.HF = var1;
   }

   public CodeGapAnalysisStyle Ef() {
      return this.HF;
   }

   public void RF(int var1) {
      this.Hk = var1;
   }

   public static enum eo {
      q,
      RF,
      xK,
      Dw,
      Uv;
   }
}
