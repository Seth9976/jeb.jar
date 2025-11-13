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
import com.pnfsoftware.jebglobal.acj;
import com.pnfsoftware.jebglobal.aco;
import com.pnfsoftware.jebglobal.aku;
import com.pnfsoftware.jebglobal.aof;
import com.pnfsoftware.jebglobal.aog;
import com.pnfsoftware.jebglobal.aoh;
import com.pnfsoftware.jebglobal.aoi;
import com.pnfsoftware.jebglobal.aok;
import com.pnfsoftware.jebglobal.aol;
import com.pnfsoftware.jebglobal.aom;
import com.pnfsoftware.jebglobal.aon;
import com.pnfsoftware.jebglobal.aop;
import com.pnfsoftware.jebglobal.aoq;
import com.pnfsoftware.jebglobal.aot;
import com.pnfsoftware.jebglobal.aou;
import com.pnfsoftware.jebglobal.aov;
import com.pnfsoftware.jebglobal.aow;
import com.pnfsoftware.jebglobal.aox;
import com.pnfsoftware.jebglobal.aoy;
import com.pnfsoftware.jebglobal.apa;
import com.pnfsoftware.jebglobal.apb;
import com.pnfsoftware.jebglobal.apc;
import com.pnfsoftware.jebglobal.ape;
import com.pnfsoftware.jebglobal.apg;
import com.pnfsoftware.jebglobal.aph;
import com.pnfsoftware.jebglobal.api;
import com.pnfsoftware.jebglobal.apj;
import com.pnfsoftware.jebglobal.apk;
import com.pnfsoftware.jebglobal.apl;
import com.pnfsoftware.jebglobal.apn;
import com.pnfsoftware.jebglobal.apo;
import com.pnfsoftware.jebglobal.apq;
import com.pnfsoftware.jebglobal.apv;
import com.pnfsoftware.jebglobal.apy;
import com.pnfsoftware.jebglobal.apz;
import com.pnfsoftware.jebglobal.aqa;
import com.pnfsoftware.jebglobal.aqc;
import com.pnfsoftware.jebglobal.aqd;
import com.pnfsoftware.jebglobal.aqe;
import com.pnfsoftware.jebglobal.aqf;
import com.pnfsoftware.jebglobal.aqg;
import com.pnfsoftware.jebglobal.aqh;
import com.pnfsoftware.jebglobal.aqi;
import com.pnfsoftware.jebglobal.aqj;
import com.pnfsoftware.jebglobal.aqm;
import com.pnfsoftware.jebglobal.aqo;
import com.pnfsoftware.jebglobal.aqp;
import com.pnfsoftware.jebglobal.aqq;
import com.pnfsoftware.jebglobal.aqr;
import com.pnfsoftware.jebglobal.aqs;
import com.pnfsoftware.jebglobal.aqt;
import com.pnfsoftware.jebglobal.aqu;
import com.pnfsoftware.jebglobal.aqv;
import com.pnfsoftware.jebglobal.aqw;
import com.pnfsoftware.jebglobal.aqx;
import com.pnfsoftware.jebglobal.aqy;
import com.pnfsoftware.jebglobal.aqz;
import com.pnfsoftware.jebglobal.arb;
import com.pnfsoftware.jebglobal.arc;
import com.pnfsoftware.jebglobal.ard;
import com.pnfsoftware.jebglobal.are;
import com.pnfsoftware.jebglobal.arf;
import com.pnfsoftware.jebglobal.arg;
import com.pnfsoftware.jebglobal.arh;
import com.pnfsoftware.jebglobal.ari;
import com.pnfsoftware.jebglobal.arj;
import com.pnfsoftware.jebglobal.ark;
import com.pnfsoftware.jebglobal.arl;
import com.pnfsoftware.jebglobal.arm;
import com.pnfsoftware.jebglobal.arn;
import com.pnfsoftware.jebglobal.aro;
import com.pnfsoftware.jebglobal.arv;
import com.pnfsoftware.jebglobal.arw;
import com.pnfsoftware.jebglobal.arx;
import com.pnfsoftware.jebglobal.ary;
import com.pnfsoftware.jebglobal.arz;
import com.pnfsoftware.jebglobal.asa;
import com.pnfsoftware.jebglobal.asb;
import com.pnfsoftware.jebglobal.asc;
import com.pnfsoftware.jebglobal.asd;
import com.pnfsoftware.jebglobal.ase;
import com.pnfsoftware.jebglobal.asf;
import com.pnfsoftware.jebglobal.asm;
import com.pnfsoftware.jebglobal.asn;
import com.pnfsoftware.jebglobal.aso;
import com.pnfsoftware.jebglobal.asp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class EMasterOptimizer extends AbstractMasterOptimizer implements IEMasterOptimizer {
   private static final StructuredLogger logger = aco.pC(EMasterOptimizer.class);
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
         this.registerOptimizer(new aqg());
         this.registerOptimizer(new aox());
         this.registerOptimizer(new aol());
         this.registerOptimizer(new arz());
         this.registerOptimizer(new apa());
         this.registerOptimizer(new aov());
         this.registerOptimizer(new aqt());
         this.registerOptimizer(new aqw());
         this.registerOptimizer(new aqv());
         this.registerOptimizer(new aqd());
         this.registerOptimizer(new aqs());
         this.registerOptimizer(new aqc());
         this.registerOptimizer(new aqx());
         this.registerOptimizer(new aoi());
         this.registerOptimizer(new apl());
         this.registerOptimizer(new aqj());
         this.registerOptimizer(new arj());
         this.registerOptimizer(new apn());
         this.registerOptimizer(new apo());
         this.registerOptimizer(new apq());
         this.registerOptimizer(new apv());
         this.registerOptimizer(new aqp());
         this.registerOptimizer(new aqq());
         this.registerOptimizer(new aqh());
         this.registerOptimizer(new aon());
         this.registerOptimizer(new aop());
         this.registerOptimizer(new aoq());
         this.registerOptimizer(new apc());
         this.registerOptimizer(new asf());
         this.registerOptimizer(new apz());
         this.registerOptimizer(new ase());
         this.registerOptimizer(new aqf());
         this.registerOptimizer(new aqi());
         this.registerOptimizer(new apy());
         this.registerOptimizer(new aot());
         this.registerOptimizer(new are());
         this.registerOptimizer(new arc());
         this.registerOptimizer(new arh());
         this.registerOptimizer(new arl());
         this.registerOptimizer(new arx());
         this.registerOptimizer(new arw());
         this.registerOptimizer(new arf());
         this.registerOptimizer(new arm());
         this.registerOptimizer(new arn());
         this.registerOptimizer(new aph());
         this.registerOptimizer(new aro());
         this.registerOptimizer(new arv());
         this.registerOptimizer(new aok());
         this.registerOptimizer(new ape());
         this.registerOptimizer(new apg());
         this.registerOptimizer(new asp());
         this.registerOptimizer(new aou());
         this.registerOptimizer(new aso());
         this.registerOptimizer(new ari());
         this.registerOptimizer(new aqo());
         this.registerOptimizer(new aqm());
         this.registerOptimizer(new asm(this.getDecryptorSupport()));
         this.registerOptimizer(new asn());
         this.registerOptimizer(new ark());
         this.registerOptimizer(new asa());
         this.registerOptimizer(new asb());
         this.registerOptimizer(new asc());
         this.registerOptimizer(new aqy());
         this.registerOptimizer(new aqz());
         this.registerOptimizer(new aqu());
         this.registerOptimizer(new arg());
         this.registerOptimizer(new aow());
         this.registerOptimizer(new ard());
         this.registerOptimizer(new arb());
         this.registerOptimizer(new apb());
         this.registerOptimizer(new aof());
         this.registerOptimizer(new ary());
         this.registerOptimizer(new aqa());
         this.registerOptimizer(new asd());
         this.registerOptimizer(2, new apj());
         this.registerOptimizer(3, new aoy());
         this.registerOptimizer(3, new apk());
         this.registerOptimizer(3, new aqe());
         this.registerOptimizer(4, new aoh());
         this.registerOptimizer(4, new aog());
         this.registerOptimizer(5, new api());
         this.registerOptimizer(5, new aqr());
         this.registerOptimizer(5, new aom());
      }
   }

   @Override
   public void setDecryptorSupport(int var1) {
      if (var1 != this.decryptorSupport) {
         this.decryptorSupport = var1;
         asm var2 = (asm)this.getOptimizerObject(asm.class);
         if (var2 != null) {
            var2.pC(this.decryptorSupport);
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
         ((aku)var3).pC(this.disregardedOutputRegisters);
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
      if (aku.E(var4)) {
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
      if (aku.E(var4)) {
         return false;
      } else if (!aku.kS(var4) && !aku.wS(var4) && !aku.UT(var4)) {
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
      acj.pC(var3, var1);
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
