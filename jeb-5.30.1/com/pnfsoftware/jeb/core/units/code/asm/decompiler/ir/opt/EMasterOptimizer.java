package com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.opt;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IERoutineContext;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEImm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVar;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IWildcardPrototype;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.VarSrc;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.opt.AbstractMasterOptimizer;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.opt.IOptimizer;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.opt.OptimizerEntry;
import com.pnfsoftware.jeb.core.units.code.asm.type.ICallingConvention;
import com.pnfsoftware.jeb.util.logging.StructuredLogger;
import com.pnfsoftware.jebglobal.aeb;
import com.pnfsoftware.jebglobal.aeg;
import com.pnfsoftware.jebglobal.amy;
import com.pnfsoftware.jebglobal.aqs;
import com.pnfsoftware.jebglobal.aqt;
import com.pnfsoftware.jebglobal.aqu;
import com.pnfsoftware.jebglobal.aqv;
import com.pnfsoftware.jebglobal.aqx;
import com.pnfsoftware.jebglobal.aqy;
import com.pnfsoftware.jebglobal.aqz;
import com.pnfsoftware.jebglobal.ara;
import com.pnfsoftware.jebglobal.arc;
import com.pnfsoftware.jebglobal.ard;
import com.pnfsoftware.jebglobal.arg;
import com.pnfsoftware.jebglobal.arh;
import com.pnfsoftware.jebglobal.ari;
import com.pnfsoftware.jebglobal.arj;
import com.pnfsoftware.jebglobal.ark;
import com.pnfsoftware.jebglobal.arm;
import com.pnfsoftware.jebglobal.arn;
import com.pnfsoftware.jebglobal.aro;
import com.pnfsoftware.jebglobal.arr;
import com.pnfsoftware.jebglobal.art;
import com.pnfsoftware.jebglobal.aru;
import com.pnfsoftware.jebglobal.arv;
import com.pnfsoftware.jebglobal.arw;
import com.pnfsoftware.jebglobal.arx;
import com.pnfsoftware.jebglobal.ary;
import com.pnfsoftware.jebglobal.asb;
import com.pnfsoftware.jebglobal.asc;
import com.pnfsoftware.jebglobal.ase;
import com.pnfsoftware.jebglobal.asj;
import com.pnfsoftware.jebglobal.asm;
import com.pnfsoftware.jebglobal.asn;
import com.pnfsoftware.jebglobal.aso;
import com.pnfsoftware.jebglobal.asq;
import com.pnfsoftware.jebglobal.asr;
import com.pnfsoftware.jebglobal.ass;
import com.pnfsoftware.jebglobal.asu;
import com.pnfsoftware.jebglobal.asv;
import com.pnfsoftware.jebglobal.asw;
import com.pnfsoftware.jebglobal.asx;
import com.pnfsoftware.jebglobal.asy;
import com.pnfsoftware.jebglobal.atb;
import com.pnfsoftware.jebglobal.atc;
import com.pnfsoftware.jebglobal.atd;
import com.pnfsoftware.jebglobal.atf;
import com.pnfsoftware.jebglobal.atg;
import com.pnfsoftware.jebglobal.ath;
import com.pnfsoftware.jebglobal.ati;
import com.pnfsoftware.jebglobal.atj;
import com.pnfsoftware.jebglobal.atk;
import com.pnfsoftware.jebglobal.atl;
import com.pnfsoftware.jebglobal.atm;
import com.pnfsoftware.jebglobal.atn;
import com.pnfsoftware.jebglobal.ato;
import com.pnfsoftware.jebglobal.atq;
import com.pnfsoftware.jebglobal.atr;
import com.pnfsoftware.jebglobal.ats;
import com.pnfsoftware.jebglobal.atv;
import com.pnfsoftware.jebglobal.atw;
import com.pnfsoftware.jebglobal.atx;
import com.pnfsoftware.jebglobal.aty;
import com.pnfsoftware.jebglobal.atz;
import com.pnfsoftware.jebglobal.aua;
import com.pnfsoftware.jebglobal.aub;
import com.pnfsoftware.jebglobal.auc;
import com.pnfsoftware.jebglobal.aud;
import com.pnfsoftware.jebglobal.aue;
import com.pnfsoftware.jebglobal.auf;
import com.pnfsoftware.jebglobal.aum;
import com.pnfsoftware.jebglobal.aun;
import com.pnfsoftware.jebglobal.auo;
import com.pnfsoftware.jebglobal.aup;
import com.pnfsoftware.jebglobal.auq;
import com.pnfsoftware.jebglobal.aur;
import com.pnfsoftware.jebglobal.aus;
import com.pnfsoftware.jebglobal.aut;
import com.pnfsoftware.jebglobal.auu;
import com.pnfsoftware.jebglobal.auv;
import com.pnfsoftware.jebglobal.auw;
import com.pnfsoftware.jebglobal.avd;
import com.pnfsoftware.jebglobal.ave;
import com.pnfsoftware.jebglobal.avf;
import com.pnfsoftware.jebglobal.avh;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class EMasterOptimizer extends AbstractMasterOptimizer implements IEMasterOptimizer {
   private static final StructuredLogger logger = aeg.q(EMasterOptimizer.class);
   public static final EMasterOptimizer EMPTY = new EMasterOptimizer();
   public static int defaultMaxRunCount = -1;
   private List disregardedOutputFilters = new ArrayList();
   private Set disregardedOutputRegisters;
   private Map defaultInput;
   private static Map spoiledVarRegIdsMap = new HashMap();

   private EMasterOptimizer() {
      super(null, 1);
   }

   public EMasterOptimizer(IERoutineContext var1) {
      this(var1, -1);
   }

   public EMasterOptimizer(IERoutineContext var1, int var2) {
      this(var1, var2, true);
   }

   public EMasterOptimizer(IERoutineContext var1, int var2, boolean var3) {
      super(var1, var2);
      if (var3) {
         this.registerOptimizer(new asv());
         this.registerOptimizer(new arj());
         this.registerOptimizer(new aqy());
         this.registerOptimizer(new auq());
         this.registerOptimizer(new arm());
         this.registerOptimizer(new ati());
         this.registerOptimizer(new atl());
         this.registerOptimizer(new atk());
         this.registerOptimizer(new asr());
         this.registerOptimizer(new ath());
         this.registerOptimizer(new asq());
         this.registerOptimizer(new atm());
         this.registerOptimizer(new aqv());
         this.registerOptimizer(new ary());
         this.registerOptimizer(new asy());
         this.registerOptimizer(new aua());
         this.registerOptimizer(new asb());
         this.registerOptimizer(new asc());
         this.registerOptimizer(new ase());
         this.registerOptimizer(new asj());
         this.registerOptimizer(new atc());
         this.registerOptimizer(new atf());
         this.registerOptimizer(new asw());
         this.registerOptimizer(new ara());
         this.registerOptimizer(new arc());
         this.registerOptimizer(new ard());
         this.registerOptimizer(new aro());
         this.registerOptimizer(new auw());
         this.registerOptimizer(new asn());
         this.registerOptimizer(new auv());
         this.registerOptimizer(new asu());
         this.registerOptimizer(new asx());
         this.registerOptimizer(new asm());
         this.registerOptimizer(new arg());
         this.registerOptimizer(new atv());
         this.registerOptimizer(new atr());
         this.registerOptimizer(new aty());
         this.registerOptimizer(new auc());
         this.registerOptimizer(new auo());
         this.registerOptimizer(new aun());
         this.registerOptimizer(new atw());
         this.registerOptimizer(new aud());
         this.registerOptimizer(new aue());
         this.registerOptimizer(new aru());
         this.registerOptimizer(new auf());
         this.registerOptimizer(new aum());
         this.registerOptimizer(new aqx());
         this.registerOptimizer(new arr());
         this.registerOptimizer(new art());
         this.registerOptimizer(new avh());
         this.registerOptimizer(new arh());
         this.registerOptimizer(new avf());
         this.registerOptimizer(new atz());
         this.registerOptimizer(new atb());
         this.registerOptimizer(new atd());
         this.registerOptimizer(new avd(this.getDecryptorSupport()));
         this.registerOptimizer(new ave());
         this.registerOptimizer(new aub());
         this.registerOptimizer(new aur());
         this.registerOptimizer(new aus());
         this.registerOptimizer(new aut());
         this.registerOptimizer(new atn());
         this.registerOptimizer(new ato());
         this.registerOptimizer(new atj());
         this.registerOptimizer(new atx());
         this.registerOptimizer(new ari());
         this.registerOptimizer(new ats());
         this.registerOptimizer(new atq());
         this.registerOptimizer(new arn());
         this.registerOptimizer(new aqs());
         this.registerOptimizer(new aup());
         this.registerOptimizer(new aso());
         this.registerOptimizer(new auu());
         this.registerOptimizer(2, new arw());
         this.registerOptimizer(3, new ark());
         this.registerOptimizer(3, new arx());
         this.registerOptimizer(3, new ass());
         this.registerOptimizer(4, new aqu());
         this.registerOptimizer(4, new aqt());
         this.registerOptimizer(5, new arv());
         this.registerOptimizer(5, new atg());
         this.registerOptimizer(5, new aqz());
      }
   }

   @Override
   public void setDecryptorSupport(int var1) {
      if (var1 != this.decryptorSupport) {
         this.decryptorSupport = var1;
         avd var2 = (avd)this.getOptimizerObject(avd.class);
         if (var2 != null) {
            var2.q(this.decryptorSupport);
         }
      }
   }

   @Override
   public void addDisregardedOutputFilter(IEOptFilterCanDiscard var1) {
      this.disregardedOutputFilters.add(var1);
   }

   @Override
   public void addDisregardedOutputBits(Collection var1) {
      if (this.disregardedOutputRegisters == null) {
         this.disregardedOutputRegisters = new HashSet();
      }

      this.disregardedOutputRegisters.addAll(var1);
   }

   @Override
   public void addDisregardedOutputVariables(Collection var1) {
      if (this.disregardedOutputRegisters == null) {
         this.disregardedOutputRegisters = new HashSet();
      }

      for (IEVar var3 : var1) {
         ((amy)var3).q(this.disregardedOutputRegisters);
      }
   }

   @Override
   public Set getDisregardedOutputRegisters() {
      return this.disregardedOutputRegisters;
   }

   @Override
   public void addDefaultInput(int var1, IEImm var2) {
      if (this.defaultInput == null) {
         this.defaultInput = new HashMap();
      }

      this.defaultInput.put(var1, var2);
   }

   @Override
   public IEImm getDefaultInput(int var1) {
      return this.defaultInput == null ? null : (IEImm)this.defaultInput.get(var1);
   }

   @Override
   public boolean canDiscardReachingOutDefinition(IERoutineContext var1, long var2, int var4) {
      if (amy.gP(var4)) {
         return false;
      } else if (var4 >= 65536 && var4 < 131072) {
         return true;
      } else if (this.disregardedOutputRegisters != null && this.disregardedOutputRegisters.contains(var4)) {
         return true;
      } else {
         for (IEOptFilterCanDiscard var6 : this.disregardedOutputFilters) {
            if (var6.check(var1.getCfg(), var2, var4)) {
               return true;
            }
         }

         VarSrc var12 = var1.getSourceForVariable(var4);
         if (var12 != null) {
            ArrayList var13 = new ArrayList();
            var12.collectSourceIds(var13);
            if (var13.size() == 1) {
               int var7 = (Integer)var13.get(0);
               if (var7 >= 65536 && var7 < 131072) {
                  return true;
               }
            }

            if (this.disregardedOutputRegisters != null && this.disregardedOutputRegisters.containsAll(var13)) {
               return true;
            }

            for (IEOptFilterCanDiscard var8 : this.disregardedOutputFilters) {
               boolean var9 = true;

               for (int var11 : var13) {
                  if (!var8.check(var1.getCfg(), var2, var11)) {
                     var9 = false;
                     break;
                  }
               }

               if (var9) {
                  return true;
               }
            }
         }

         return false;
      }
   }

   @Override
   public boolean canDiscardUnusedDefinition(IERoutineContext var1, long var2, int var4) {
      if (amy.gP(var4)) {
         return false;
      } else if (!amy.xK(var4) && !amy.Uv(var4) && !amy.gO(var4)) {
         if (this.disregardedOutputRegisters != null && this.disregardedOutputRegisters.contains(var4)) {
            return true;
         } else {
            for (IEOptFilterCanDiscard var6 : this.disregardedOutputFilters) {
               if (var6.check(var1.getCfg(), var2, var4)) {
                  return true;
               }
            }

            VarSrc var12 = var1.getSourceForVariable(var4);
            if (var12 != null) {
               ArrayList var13 = new ArrayList();
               var12.collectSourceIds(var13);
               if (var13.size() == 1) {
                  int var7 = (Integer)var13.get(0);
                  if (var7 >= 65536 && var7 < 131072) {
                     return true;
                  }
               }

               if (this.disregardedOutputRegisters != null && this.disregardedOutputRegisters.containsAll(var13)) {
                  return true;
               }

               for (IEOptFilterCanDiscard var8 : this.disregardedOutputFilters) {
                  boolean var9 = true;

                  for (int var11 : var13) {
                     if (!var8.check(var1.getCfg(), var2, var11)) {
                        var9 = false;
                        break;
                     }
                  }

                  if (var9) {
                     return true;
                  }
               }
            }

            return false;
         }
      } else {
         return true;
      }
   }

   private static Collection getSpoiledVarRegIds(IERoutineContext var0, IWildcardPrototype var1) {
      ICallingConvention var2 = var1.getCallingConvention();
      Object var3 = (Collection)spoiledVarRegIdsMap.get(var2.getIdentifierKey());
      if (var3 == null) {
         var3 = new HashSet();

         for (long var5 : var2.getPureSpoiledRegisters()) {
            IEGeneric var7 = var0.getConverter().getRegisterVariableFromNativeRegisterId(var5);
            if (var7 instanceof IEVar) {
               int var8 = ((IEVar)var7).getId();
               var3.add(var8);
            }
         }

         spoiledVarRegIdsMap.put(var2.getIdentifierKey(), var3);
      }

      return (Collection)var3;
   }

   protected boolean onOptimizerException(IERoutineContext var1, IOptimizer var2, Exception var3) {
      boolean var4 = super.onOptimizerException(var1, var2, var3);
      var1.getCfg();
      aeb.q(var3, var1);
      return var4;
   }

   protected void preAllOptimizationsCallback(IERoutineContext var1) {
      super.preAllOptimizationsCallback(var1);
   }

   protected void preOptimizationCallback(IERoutineContext var1, OptimizerEntry var2) {
      super.preOptimizationCallback(var1, var2);
   }

   protected void postOptimizationCallback(IERoutineContext var1, OptimizerEntry var2, int var3, long var4) {
      super.postOptimizationCallback(var1, var2, var3, var4);
   }

   protected void postAllOptimizationsCallback(IERoutineContext var1) {
      super.postAllOptimizationsCallback(var1);
      this.debugCheckGraph();
   }

   private void debugCheckGraph() {
   }

   protected String getTargetAddress(IERoutineContext var1) {
      return var1.getRoutine().getAddress(false);
   }
}
