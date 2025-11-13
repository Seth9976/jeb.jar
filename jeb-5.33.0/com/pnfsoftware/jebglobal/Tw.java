package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.IFlowInformation;
import com.pnfsoftware.jeb.core.units.code.IInstruction;
import com.pnfsoftware.jeb.core.units.code.NativeCommentManager;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.AutoLabelPolicy;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.BranchTarget;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.IBranchTarget;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.ICallGraph;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.ICallGraphManager;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.ICommentManager;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.INativeCodeModel;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.ReferenceType;
import com.pnfsoftware.jeb.core.units.code.asm.cfg.BasicBlock;
import com.pnfsoftware.jeb.core.units.code.asm.items.IMethodManager;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeContinuousItem;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeInstructionItem;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeItemListener;
import com.pnfsoftware.jeb.core.units.code.asm.items.InstructionHints;
import com.pnfsoftware.jeb.core.units.code.asm.items.NativeItemEvent;
import com.pnfsoftware.jeb.core.units.code.asm.items.NativeItemEventType;
import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.collect.AddressHashMap;
import com.pnfsoftware.jeb.util.collect.AddressHashSet;
import com.pnfsoftware.jeb.util.collect.AddressTreeMap;
import com.pnfsoftware.jeb.util.collect.ISegmentFactory;
import com.pnfsoftware.jeb.util.concurrent.ACLock;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerCustomInitPostGraph;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import com.pnfsoftware.jeb.util.serialization.annotations.SerTransient;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.Map.Entry;

@Ser
public class Tw extends ZH implements INativeCodeModel, INativeItemListener {
   private static final ILogger kS = GlobalLog.getLogger(Tw.class);
   @SerId(1)
   private ko wS;
   @SerId(2)
   private AddressHashMap UT;
   @SerId(3)
   private AddressTreeMap E;
   @SerId(4)
   private AddressTreeMap sY;
   @SerId(value = 6, deprecated = true)
   private d ys;
   @SerId(7)
   private ey ld;
   @SerId(8)
   private ph gp;
   @SerId(9)
   private AddressHashMap oT;
   @SerId(10)
   private AddressHashMap fI;
   @SerId(11)
   private AddressHashSet WR;
   @SerId(12)
   private Gd NS;
   @SerId(13)
   private IMethodManager vP;
   @SerId(14)
   private Collection xC = new HashSet();
   @SerId(15)
   private ICallGraphManager ED;
   @SerId(16)
   NativeCommentManager A;
   @SerTransient
   private ISegmentFactory Sc;
   @SerTransient
   private ICommentManager ah;

   @SerCustomInitPostGraph
   private void fI() {
      this.Sc = new NP(this);
      if (this.ED == null) {
         this.ED = new fe(this);
         if (this.NS != null) {
            ICallGraph var1 = this.NS.pC();
            ((fe)this.ED).pC(var1);
            this.NS = null;
         }
      }

      if (this.ah == null) {
         this.ah = new dI(this);
      }
   }

   public Tw(int var1, IMethodManager var2, ko var3, NativeCommentManager var4, rn var5) {
      super(var1, false, var3, var5);
      this.wS = var3;
      this.vP = var2;
      this.UT = new AddressHashMap(var1);
      this.E = new AddressTreeMap(var1);
      this.sY = new AddressTreeMap(var1);
      this.oT = new AddressHashMap(var1);
      this.fI = new AddressHashMap(var1);
      this.WR = new AddressHashSet(var1);
      this.A = var4;
      this.ld = new ey(this);
      this.gp = new ph(this);
      this.ED = new fe(this);
      this.fI();
   }

   @Override
   public void onNativeItemEvent(NativeItemEvent var1) {
      if (var1.getType() == NativeItemEventType.DISPOSED && var1.getItem() instanceof aun) {
         ((aun)var1.getItem()).removeListener(this);
      }

      this.wS.onNativeItemEvent(var1);
   }

   public void pC(INativeContinuousItem var1, boolean var2) {
      try (ACLock var3 = this.pC.a()) {
         ((aul)var1).xC();
         long var4 = var1.getMemoryAddress();
         if (!var2) {
            this.gp.removeLabel(var4);
         } else {
            String var6 = this.gp.getLabel(var4, 0L, AutoLabelPolicy.OFF);
            if (var6 != null && this.gp.pC(var6)) {
               this.gp.removeLabel(var4);
            }
         }

         if (var1 instanceof aus) {
            this.ED.getGlobalCallGraph().removeCallRelationship(var1.getMemoryAddress());
         }
      }
   }

   @Override
   public void pC(long var1, boolean var3) {
      this.pC.verifyLocked();
      if (!var3) {
         this.A(var1);
         this.UT(var1, false);
      }

      aut var4 = this.wS(var1);
      if (var4 != null) {
         this.A(var4);
      }
   }

   @Override
   public void pC(long var1) {
      this.pC.verifyLocked();
      aul var3 = (aul)this.getItemAt(var1);
      if (var3 != null) {
         if (var3 instanceof aus) {
            ON var4 = (ON)this.UT.get(var3.getMemoryAddress());
            if (var4 != null) {
               ArrayList var5 = new ArrayList(var4.UT);
               if (!var5.isEmpty()) {
                  boolean var6 = true;

                  for (Ss var8 : var5) {
                     aut var9 = (aut)this.sY.get(var8.pC);
                     if (var9 != null) {
                        for (auu var11 : var9.getMethodReferences()) {
                           if (!var11.isDisposed()) {
                              var11.xC();
                              var6 = false;
                           }
                        }
                     }
                  }

                  if (var6) {
                     HashSet var12 = new HashSet();

                     for (Ss var16 : var5) {
                        boolean var19 = var4.pC == var16.pC;
                        if (!var19) {
                           var19 = this.pC(var16, var4);
                        }

                        if (var19) {
                           var12.add(var16);
                        }
                     }

                     for (Ss var17 : var12) {
                        this.A(var17.pC);
                     }

                     for (Ss var18 : var12) {
                        this.UT(var18.pC, false);
                        aut var20 = this.wS(var18.pC);
                        if (var20 != null) {
                           this.A(var20);
                        }
                     }
                  }
               }
            }
         }

         this.pC(var3);
      }
   }

   private boolean pC(Ss var1, ON var2) {
      List var3 = this.pC(var1, var2.pC);
      if (var3.isEmpty()) {
         return false;
      } else {
         for (ON var5 : var3) {
            IInstruction var6 = (IInstruction)var5.A.get(var5.A.size() - 1);
            IFlowInformation var7 = var6.getRoutineCall(var5.getLastAddress());
            if (!var7.isBroken() && var5.kS.size() == 1) {
               var5.pC(Long.valueOf(var2.pC));
               if (var5.pC == var1.pC || this.pC(var1, var5)) {
                  return true;
               }
            } else if (var5.getEndAddress() == var2.pC) {
               if (!var7.isBroken()) {
                  return true;
               }

               var5.pC(Long.valueOf(var2.pC));
            } else {
               var5.pC(Long.valueOf(var2.pC));
               if (var5.pC == var1.pC || this.pC(var1, var5)) {
                  return true;
               }
            }
         }

         return false;
      }
   }

   private List pC(Ss var1, long var2) {
      ArrayList var4 = new ArrayList();

      for (ON var6 : var1.A) {
         if (var6.kS(var2)) {
            var4.add(var6);
         }
      }

      return var4;
   }

   boolean A(long var1) {
      this.pC.verifyLocked();
      Ss var3 = this.ys(var1);
      if (var3 == null) {
         return false;
      } else {
         ArrayList var4 = new ArrayList();

         for (ON var6 : var3.A) {
            var6.UT.remove(var3);
            if (var6.UT.isEmpty()) {
               var4.add(var6);
            }
         }

         for (ON var12 : var4) {
            long var7 = var12.pC;

            for (int var9 = 0; var9 < var12.A.size(); var9++) {
               IInstruction var10 = (IInstruction)var12.A.get(var9);
               this.UT.remove(var7);
               if (var9 + 1 == var12.A.size()) {
                  this.fI(var7);
               }

               var7 += var10.getSize();
            }

            var12.A.clear();
            var12.kS.clear();
            var12.wS.clear();
            var12.E = null;
         }

         return true;
      }
   }

   @Override
   void wS() {
      this.pC.verifyLocked();
   }

   void UT() {
      this.pC.verifyLocked();
      Iterator var1 = this.xC.iterator();

      while (var1.hasNext()) {
         ((auu)var1.next()).xC();
         var1.remove();
      }
   }

   public boolean A(long var1, boolean var3) {
      this.pC.verifyLocked();
      aut var4 = this.wS(var1);
      if (var4 == null) {
         return false;
      } else {
         if (var3) {
            this.xC.addAll(var4.getMethodReferences());
            var4.pC(true);
         }

         var4.xC();
         return true;
      }
   }

   void pC(aut var1) {
      this.pC.verifyLocked();
      Assert.a(var1 != null);
      var1.pC(this);
      long var2 = var1.getMemoryAddress();
      int var4 = 0;
      Iterator var5 = this.xC.iterator();

      while (var5.hasNext()) {
         auu var6 = (auu)var5.next();
         if (var6.getMemoryAddress() != null && var6.getMemoryAddress() == var2) {
            var6.pC(var1, true);
            var5.remove();
            var4++;
         }
      }

      if (var4 == 0) {
         this.vP.createMethodReference(null, null, var1);
      }

      this.sY.put(var2, var1);
   }

   void A(aut var1) {
      this.pC.verifyLocked();
      this.sY.remove(var1.getMemoryAddress());
   }

   boolean kS(long var1) {
      return this.sY.containsKey(var1);
   }

   public Collection E() {
      return new ArrayList(this.sY.values());
   }

   public aut wS(long var1) {
      return (aut)this.sY.get(var1);
   }

   public auu UT(long var1) {
      Entry var3 = this.sY.higherEntry(var1);
      return var3 == null ? null : ((aut)var3.getValue()).kS();
   }

   public auu E(long var1) {
      return this.kS(var1, true);
   }

   public auu kS(long var1, boolean var3) {
      aut var4 = this.wS(var1);
      if (!var3 && var4 == null) {
         Entry var5 = this.sY.floorEntry(var1);
         if (var5 != null && ((aut)var5.getValue()).A(var1)) {
            return ((aut)var5.getValue()).kS();
         } else {
            INativeContinuousItem var6 = this.getItemOver(var1);
            if (var6 instanceof INativeInstructionItem) {
               long var7 = var6.getMemoryAddress();
               ON var9 = (ON)this.UT.get(var7);
               if (var9 != null && !var9.UT.isEmpty()) {
                  try {
                     var1 = ((Ss)var9.UT.get(0)).pC;
                  } catch (Exception var10) {
                     return null;
                  }

                  var4 = this.wS(var1);
                  if (var4 != null) {
                     return var4.kS();
                  }
               }
            }

            return null;
         }
      } else {
         return var4 == null ? null : var4.kS();
      }
   }

   public Collection sY() {
      ArrayList var1 = new ArrayList(this.sY.size());

      for (aut var3 : this.sY.values()) {
         var1.addAll(var3.getMethodReferences());
      }

      return var1;
   }

   public int ys() {
      return this.sY.size();
   }

   @Override
   public ISegmentFactory getGapFactory() {
      return this.Sc;
   }

   public String pC(long var1, long var3, AutoLabelPolicy var5) {
      return this.gp.getLabel(var1, var3, var5);
   }

   public boolean pC(long var1, String var3) {
      return this.gp.setLabel(var1, var3, true, false, false);
   }

   void pC(Ss var1) {
      this.pC.verifyLocked();
      this.E.put(var1.pC, var1);
   }

   void sY(long var1) {
      this.UT(var1, true);
   }

   private void UT(long var1, boolean var3) {
      if (var3) {
         this.pC.verifyLocked();
      }

      this.E.remove(var1);
   }

   Ss ys(long var1) {
      return (Ss)this.E.get(var1);
   }

   Collection ld() {
      return this.E.values();
   }

   @Override
   public boolean isBasicBlockHeader(long var1) {
      ON var3 = (ON)this.UT.get(var1);
      return var3 != null && var3.pC == var1 && var3.E != null;
   }

   @Override
   public BasicBlock getBasicBlockHeader(long var1) {
      ON var3 = (ON)this.UT.get(var1);
      return var3 != null && var3.pC == var1 && var3.E != null ? var3.E : null;
   }

   @Override
   public boolean isRoutineHeader(long var1) {
      return this.sY.containsKey(var1);
   }

   @Override
   public List getContainedRoutineAddresses(long var1) {
      return this.wS(var1, false);
   }

   public List wS(long var1, boolean var3) {
      ON var4 = (ON)this.UT.get(var1);
      if (var4 == null) {
         return Collections.emptyList();
      } else {
         ArrayList var5 = new ArrayList();

         for (Ss var7 : var4.UT) {
            if (var3 || this.sY.containsKey(var7.pC)) {
               var5.add(var7.pC);
            }
         }

         return var5;
      }
   }

   @Override
   public List getRoutineAddresses() {
      return Collections.unmodifiableList(new ArrayList(this.sY.keySet()));
   }

   List pC(long var1, boolean var3, Set var4, boolean var5) {
      this.pC.verifyLocked();
      List var6 = this.A(var1, var3, var4, var5);
      if (var6 == null) {
         return null;
      } else {
         ArrayList var7 = new ArrayList();

         for (long var9 : var6) {
            var7.add((ON)this.UT.get(var9));
         }

         return var7;
      }
   }

   private List A(long var1, boolean var3, Set var4, boolean var5) {
      ArrayList var6 = new ArrayList();
      if (var3) {
         var6.add(var1);
      }

      return !this.pC(var1, var6, var4, var5) ? null : var6;
   }

   private boolean pC(long var1, List var3, Set var4, boolean var5) {
      ArrayDeque var6 = new ArrayDeque();
      var6.add(var1);

      while (!var6.isEmpty()) {
         var1 = (Long)var6.pop();
         ON var7 = (ON)this.UT.get(var1);
         if (var7 == null) {
            Object[] var10000 = new Object[]{var1};
            return false;
         }

         if (var7.wS != null && !var7.wS.isEmpty()) {
            throw new RuntimeException("Irregular flows not supported yet");
         }

         for (int var8 = 0; var8 < var7.kS.size(); var8++) {
            long var9 = (Long)var7.kS.get(var8);
            if (var4 != null && var4.contains(var9)) {
               var7.A(var8);
               var8--;
            } else if (!var3.contains(var9)) {
               if (var5 && !this.UT.containsKey(var1)) {
                  var7.A(var8);
                  var8--;
               } else {
                  var3.add(var9);
                  var6.add(var9);
               }
            }
         }
      }

      return true;
   }

   void pC(long var1, ON var3) {
      this.pC.verifyLocked();
      this.UT.put(var1, var3);
   }

   boolean ld(long var1) {
      this.pC.verifyLocked();
      return this.UT.remove(var1) != null;
   }

   ON gp(long var1) {
      return (ON)this.UT.get(var1);
   }

   @Override
   public ICommentManager getCommentManager() {
      return this.ah;
   }

   public ey gp() {
      return this.ld;
   }

   public ph oT() {
      return this.gp;
   }

   @Override
   public ICallGraphManager getCallGraphManager() {
      return this.ED;
   }

   boolean pC(long var1, boolean var3, BranchTarget var4) {
      this.pC.verifyLocked();
      Me var5 = (Me)this.oT.get(var1);
      if (var5 == null) {
         var5 = new Me();
         this.oT.put(var1, var5);
      }

      if (var4.isInternal()) {
         long var6 = var4.getInternalAddress().getAddress();
         this.ld.recordInternalReference(var1, var6, ReferenceType.DYNAMIC_BRANCH);
      }

      return var3 ? var5.pC(var4) : var5.A(var4);
   }

   boolean A(long var1, boolean var3, BranchTarget var4) {
      this.pC.verifyLocked();
      Me var5 = (Me)this.oT.get(var1);
      if (var5 == null) {
         return false;
      } else if (var4 == null) {
         if (var3) {
            var5.pC(null);
         } else {
            var5.A();
         }

         return true;
      } else {
         if (var4.isInternal()) {
            long var6 = var4.getInternalAddress().getAddress();
            this.ld.unrecordReference(var6, var1);
         }

         if (var3) {
            return !var4.equals(var5.pC()) ? false : var5.pC(null);
         } else {
            return var5.pC((IBranchTarget)var4);
         }
      }
   }

   boolean pC(long var1, boolean var3, long var4, long var6) {
      this.pC.verifyLocked();
      kf var10 = (kf)this.fI.get(var1);
      if (var10 == null) {
         var10 = new kf();
         this.fI.put(var1, var10);
      }

      this.ld.recordInternalReference(var1, var6, ReferenceType.GEN_DATA, 3);
      return var10.pC(this.pC, var3, var4, var6);
   }

   boolean oT(long var1) {
      this.pC.verifyLocked();
      return this.WR.add(var1);
   }

   boolean fI(long var1) {
      this.pC.verifyLocked();
      return this.WR.remove(var1);
   }

   @Override
   public boolean isArtificialEndOfBlock(long var1) {
      return this.WR.contains(var1);
   }

   @Override
   public boolean isReversedBranchSemantic(long var1) {
      INativeContinuousItem var3 = this.getItemAt(var1);
      if (var3 instanceof INativeInstructionItem) {
         InstructionHints var4 = ((INativeInstructionItem)var3).getHints(false);
         if (var4 != null && (var4.isFakeCall() || var4.isActualCall())) {
            return true;
         }
      }

      return false;
   }

   public Me WR(long var1) {
      Me var3 = (Me)this.oT.get(var1);
      if (var3 == null) {
         var3 = Me.pC;
      }

      return var3;
   }

   public kf NS(long var1) {
      kf var3 = (kf)this.fI.get(var1);
      if (var3 == null) {
         var3 = kf.pC;
      }

      return var3;
   }

   @Override
   protected void A() {
      super.A();

      for (Entry var2 : this.sY.entrySet()) {
         ((aut)var2.getValue()).dispose(false);
      }
   }

   @Override
   public void kS() {
      super.kS();
      this.wS = null;
      this.UT = null;
      this.E = null;
      this.sY = null;
      this.A = null;
      this.ld = null;
      this.gp = null;
      this.oT = null;
      this.fI = null;
      this.WR = null;
      this.NS = null;
      this.vP = null;
      this.xC = null;
      this.ED = null;
   }
}
