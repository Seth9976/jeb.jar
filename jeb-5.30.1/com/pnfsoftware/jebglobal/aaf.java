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
public class aaf extends Bm implements INativeCodeModel, INativeItemListener {
   private static final ILogger xK = GlobalLog.getLogger(aaf.class);
   @SerId(1)
   private aam Dw;
   @SerId(2)
   private AddressHashMap Uv;
   @SerId(3)
   private AddressTreeMap oW;
   @SerId(4)
   private AddressTreeMap gO;
   @SerId(value = 6, deprecated = true)
   private abo nf;
   @SerId(7)
   private abl gP;
   @SerId(8)
   private v za;
   @SerId(9)
   private AddressHashMap lm;
   @SerId(10)
   private AddressHashMap zz;
   @SerId(11)
   private AddressHashSet JY;
   @SerId(12)
   private WP HF;
   @SerId(13)
   private IMethodManager LK;
   @SerId(14)
   private Collection io = new HashSet();
   @SerId(15)
   private ICallGraphManager qa;
   @SerId(16)
   NativeCommentManager RF;
   @SerTransient
   private ISegmentFactory Hk;
   @SerTransient
   private ICommentManager Me;

   @SerCustomInitPostGraph
   private void io() {
      this.Hk = new aag(this);
      if (this.qa == null) {
         this.qa = new VA(this);
         if (this.HF != null) {
            ICallGraph var1 = this.HF.q();
            ((VA)this.qa).q(var1);
            this.HF = null;
         }
      }

      if (this.Me == null) {
         this.Me = new aah(this);
      }
   }

   public aaf(int var1, IMethodManager var2, aam var3, NativeCommentManager var4, be var5) {
      super(var1, false, var3, var5);
      this.Dw = var3;
      this.LK = var2;
      this.Uv = new AddressHashMap(var1);
      this.oW = new AddressTreeMap(var1);
      this.gO = new AddressTreeMap(var1);
      this.lm = new AddressHashMap(var1);
      this.zz = new AddressHashMap(var1);
      this.JY = new AddressHashSet(var1);
      this.RF = var4;
      this.gP = new abl(this);
      this.za = new v(this);
      this.qa = new VA(this);
      this.io();
   }

   @Override
   public void onNativeItemEvent(NativeItemEvent var1) {
      if (var1.getType() == NativeItemEventType.DISPOSED && var1.getItem() instanceof axi) {
         ((axi)var1.getItem()).removeListener(this);
      }

      this.Dw.onNativeItemEvent(var1);
   }

   public void q(INativeContinuousItem var1, boolean var2) {
      try (ACLock var3 = this.q.a()) {
         ((axg)var1).PV();
         long var4 = var1.getMemoryAddress();
         if (!var2) {
            this.za.removeLabel(var4);
         } else {
            String var6 = this.za.getLabel(var4, 0L, AutoLabelPolicy.OFF);
            if (var6 != null && this.za.q(var6)) {
               this.za.removeLabel(var4);
            }
         }

         if (var1 instanceof axn) {
            this.qa.getGlobalCallGraph().removeCallRelationship(var1.getMemoryAddress());
         }
      }
   }

   @Override
   public void q(long var1, boolean var3) {
      this.q.verifyLocked();
      if (!var3) {
         this.RF(var1);
         this.Uv(var1, false);
      }

      axo var4 = this.Dw(var1);
      if (var4 != null) {
         this.RF(var4);
      }
   }

   @Override
   public void q(long var1) {
      this.q.verifyLocked();
      axg var3 = (axg)this.getItemAt(var1);
      if (var3 != null) {
         if (var3 instanceof axn) {
            kR var4 = (kR)this.Uv.get(var3.getMemoryAddress());
            if (var4 != null) {
               ArrayList var5 = new ArrayList(var4.Uv);
               if (!var5.isEmpty()) {
                  boolean var6 = true;

                  for (Xg var8 : var5) {
                     axo var9 = (axo)this.gO.get(var8.q);
                     if (var9 != null) {
                        for (axp var11 : var9.getMethodReferences()) {
                           if (!var11.isDisposed()) {
                              var11.PV();
                              var6 = false;
                           }
                        }
                     }
                  }

                  if (var6) {
                     HashSet var12 = new HashSet();

                     for (Xg var16 : var5) {
                        boolean var19 = var4.q == var16.q;
                        if (!var19) {
                           var19 = this.q(var16, var4);
                        }

                        if (var19) {
                           var12.add(var16);
                        }
                     }

                     for (Xg var17 : var12) {
                        this.RF(var17.q);
                     }

                     for (Xg var18 : var12) {
                        this.Uv(var18.q, false);
                        axo var20 = this.Dw(var18.q);
                        if (var20 != null) {
                           this.RF(var20);
                        }
                     }
                  }
               }
            }
         }

         this.q(var3);
      }
   }

   private boolean q(Xg var1, kR var2) {
      List var3 = this.q(var1, var2.q);
      if (var3.isEmpty()) {
         return false;
      } else {
         for (kR var5 : var3) {
            IInstruction var6 = (IInstruction)var5.RF.get(var5.RF.size() - 1);
            IFlowInformation var7 = var6.getRoutineCall(var5.getLastAddress());
            if (!var7.isBroken() && var5.xK.size() == 1) {
               var5.q(Long.valueOf(var2.q));
               if (var5.q == var1.q || this.q(var1, var5)) {
                  return true;
               }
            } else if (var5.getEndAddress() == var2.q) {
               if (!var7.isBroken()) {
                  return true;
               }

               var5.q(Long.valueOf(var2.q));
            } else {
               var5.q(Long.valueOf(var2.q));
               if (var5.q == var1.q || this.q(var1, var5)) {
                  return true;
               }
            }
         }

         return false;
      }
   }

   private List q(Xg var1, long var2) {
      ArrayList var4 = new ArrayList();

      for (kR var6 : var1.RF) {
         if (var6.xK(var2)) {
            var4.add(var6);
         }
      }

      return var4;
   }

   boolean RF(long var1) {
      this.q.verifyLocked();
      Xg var3 = this.nf(var1);
      if (var3 == null) {
         return false;
      } else {
         ArrayList var4 = new ArrayList();

         for (kR var6 : var3.RF) {
            var6.Uv.remove(var3);
            if (var6.Uv.isEmpty()) {
               var4.add(var6);
            }
         }

         for (kR var12 : var4) {
            long var7 = var12.q;

            for (int var9 = 0; var9 < var12.RF.size(); var9++) {
               IInstruction var10 = (IInstruction)var12.RF.get(var9);
               this.Uv.remove(var7);
               if (var9 + 1 == var12.RF.size()) {
                  this.JY(var7);
               }

               var7 += var10.getSize();
            }

            var12.RF.clear();
            var12.xK.clear();
            var12.Dw.clear();
            var12.oW = null;
         }

         return true;
      }
   }

   @Override
   void oW() {
      this.q.verifyLocked();
   }

   void gO() {
      this.q.verifyLocked();
      Iterator var1 = this.io.iterator();

      while (var1.hasNext()) {
         ((axp)var1.next()).PV();
         var1.remove();
      }
   }

   public boolean RF(long var1, boolean var3) {
      this.q.verifyLocked();
      axo var4 = this.Dw(var1);
      if (var4 == null) {
         return false;
      } else {
         if (var3) {
            this.io.addAll(var4.getMethodReferences());
            var4.q(true);
         }

         var4.PV();
         return true;
      }
   }

   void q(axo var1) {
      this.q.verifyLocked();
      Assert.a(var1 != null);
      var1.q(this);
      long var2 = var1.getMemoryAddress();
      int var4 = 0;
      Iterator var5 = this.io.iterator();

      while (var5.hasNext()) {
         axp var6 = (axp)var5.next();
         if (var6.getMemoryAddress() != null && var6.getMemoryAddress() == var2) {
            var6.q(var1, true);
            var5.remove();
            var4++;
         }
      }

      if (var4 == 0) {
         this.LK.createMethodReference(null, null, var1);
      }

      this.gO.put(var2, var1);
   }

   void RF(axo var1) {
      this.q.verifyLocked();
      this.gO.remove(var1.getMemoryAddress());
   }

   boolean xK(long var1) {
      return this.gO.containsKey(var1);
   }

   public Collection nf() {
      return new ArrayList(this.gO.values());
   }

   public axo Dw(long var1) {
      return (axo)this.gO.get(var1);
   }

   public axp Uv(long var1) {
      Entry var3 = this.gO.higherEntry(var1);
      return var3 == null ? null : ((axo)var3.getValue()).xK();
   }

   public axp oW(long var1) {
      return this.xK(var1, true);
   }

   public axp xK(long var1, boolean var3) {
      axo var4 = this.Dw(var1);
      if (!var3 && var4 == null) {
         Entry var5 = this.gO.floorEntry(var1);
         if (var5 != null && ((axo)var5.getValue()).RF(var1)) {
            return ((axo)var5.getValue()).xK();
         } else {
            INativeContinuousItem var6 = this.getItemOver(var1);
            if (var6 instanceof INativeInstructionItem) {
               long var7 = var6.getMemoryAddress();
               kR var9 = (kR)this.Uv.get(var7);
               if (var9 != null && !var9.Uv.isEmpty()) {
                  try {
                     var1 = ((Xg)var9.Uv.get(0)).q;
                  } catch (Exception var10) {
                     return null;
                  }

                  var4 = this.Dw(var1);
                  if (var4 != null) {
                     return var4.xK();
                  }
               }
            }

            return null;
         }
      } else {
         return var4 == null ? null : var4.xK();
      }
   }

   public Collection gP() {
      ArrayList var1 = new ArrayList(this.gO.size());

      for (axo var3 : this.gO.values()) {
         var1.addAll(var3.getMethodReferences());
      }

      return var1;
   }

   public int za() {
      return this.gO.size();
   }

   @Override
   public ISegmentFactory getGapFactory() {
      return this.Hk;
   }

   public String q(long var1, long var3, AutoLabelPolicy var5) {
      return this.za.getLabel(var1, var3, var5);
   }

   public boolean q(long var1, String var3) {
      return this.za.setLabel(var1, var3, true, false, false);
   }

   void q(Xg var1) {
      this.q.verifyLocked();
      this.oW.put(var1.q, var1);
   }

   void gO(long var1) {
      this.Uv(var1, true);
   }

   private void Uv(long var1, boolean var3) {
      if (var3) {
         this.q.verifyLocked();
      }

      this.oW.remove(var1);
   }

   Xg nf(long var1) {
      return (Xg)this.oW.get(var1);
   }

   Collection lm() {
      return this.oW.values();
   }

   Collection zz() {
      return this.oW.keySet();
   }

   BasicBlock gP(long var1) {
      kR var3 = (kR)this.Uv.get(var1);
      return var3 == null ? null : var3.q();
   }

   @Override
   public boolean isBasicBlockHeader(long var1) {
      kR var3 = (kR)this.Uv.get(var1);
      return var3 != null && var3.q == var1 && var3.oW != null;
   }

   @Override
   public BasicBlock getBasicBlockHeader(long var1) {
      kR var3 = (kR)this.Uv.get(var1);
      return var3 != null && var3.q == var1 && var3.oW != null ? var3.oW : null;
   }

   @Override
   public boolean isRoutineHeader(long var1) {
      return this.gO.containsKey(var1);
   }

   @Override
   public List getContainedRoutineAddresses(long var1) {
      return this.Dw(var1, false);
   }

   public List Dw(long var1, boolean var3) {
      kR var4 = (kR)this.Uv.get(var1);
      if (var4 == null) {
         return Collections.emptyList();
      } else {
         ArrayList var5 = new ArrayList();

         for (Xg var7 : var4.Uv) {
            if (var3 || this.gO.containsKey(var7.q)) {
               var5.add(var7.q);
            }
         }

         return var5;
      }
   }

   @Override
   public List getRoutineAddresses() {
      return Collections.unmodifiableList(new ArrayList(this.gO.keySet()));
   }

   List q(long var1, boolean var3, Set var4, boolean var5) {
      this.q.verifyLocked();
      List var6 = this.RF(var1, var3, var4, var5);
      if (var6 == null) {
         return null;
      } else {
         ArrayList var7 = new ArrayList();

         for (long var9 : var6) {
            var7.add((kR)this.Uv.get(var9));
         }

         return var7;
      }
   }

   private List RF(long var1, boolean var3, Set var4, boolean var5) {
      ArrayList var6 = new ArrayList();
      if (var3) {
         var6.add(var1);
      }

      return !this.q(var1, var6, var4, var5) ? null : var6;
   }

   private boolean q(long var1, List var3, Set var4, boolean var5) {
      ArrayDeque var6 = new ArrayDeque();
      var6.add(var1);

      while (!var6.isEmpty()) {
         var1 = (Long)var6.pop();
         kR var7 = (kR)this.Uv.get(var1);
         if (var7 == null) {
            Object[] var10000 = new Object[]{var1};
            return false;
         }

         if (var7.Dw != null && !var7.Dw.isEmpty()) {
            throw new RuntimeException("Irregular flows not supported yet");
         }

         for (int var8 = 0; var8 < var7.xK.size(); var8++) {
            long var9 = (Long)var7.xK.get(var8);
            if (var4 != null && var4.contains(var9)) {
               var7.RF(var8);
               var8--;
            } else if (!var3.contains(var9)) {
               if (var5 && !this.Uv.containsKey(var1)) {
                  var7.RF(var8);
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

   void q(long var1, kR var3) {
      this.q.verifyLocked();
      this.Uv.put(var1, var3);
   }

   boolean za(long var1) {
      this.q.verifyLocked();
      return this.Uv.remove(var1) != null;
   }

   Collection JY() {
      return this.Uv.values();
   }

   kR lm(long var1) {
      return (kR)this.Uv.get(var1);
   }

   @Override
   public ICommentManager getCommentManager() {
      return this.Me;
   }

   public abl HF() {
      return this.gP;
   }

   public v LK() {
      return this.za;
   }

   @Override
   public ICallGraphManager getCallGraphManager() {
      return this.qa;
   }

   boolean q(long var1, boolean var3, BranchTarget var4) {
      this.q.verifyLocked();
      qz var5 = (qz)this.lm.get(var1);
      if (var5 == null) {
         var5 = new qz();
         this.lm.put(var1, var5);
      }

      if (var4.isInternal()) {
         long var6 = var4.getInternalAddress().getAddress();
         this.gP.recordInternalReference(var1, var6, ReferenceType.DYNAMIC_BRANCH);
      }

      return var3 ? var5.q(var4) : var5.RF(var4);
   }

   boolean RF(long var1, boolean var3, BranchTarget var4) {
      this.q.verifyLocked();
      qz var5 = (qz)this.lm.get(var1);
      if (var5 == null) {
         return false;
      } else if (var4 == null) {
         if (var3) {
            var5.q(null);
         } else {
            var5.RF();
         }

         return true;
      } else {
         if (var4.isInternal()) {
            long var6 = var4.getInternalAddress().getAddress();
            this.gP.unrecordReference(var6, var1);
         }

         if (var3) {
            return !var4.equals(var5.q()) ? false : var5.q(null);
         } else {
            return var5.q((IBranchTarget)var4);
         }
      }
   }

   boolean q(long var1, boolean var3, long var4, long var6) {
      this.q.verifyLocked();
      abn var10 = (abn)this.zz.get(var1);
      if (var10 == null) {
         var10 = new abn();
         this.zz.put(var1, var10);
      }

      this.gP.recordInternalReference(var1, var6, ReferenceType.GEN_DATA, 3);
      return var10.q(this.q, var3, var4, var6);
   }

   boolean zz(long var1) {
      this.q.verifyLocked();
      return this.JY.add(var1);
   }

   boolean JY(long var1) {
      this.q.verifyLocked();
      return this.JY.remove(var1);
   }

   @Override
   public boolean isArtificialEndOfBlock(long var1) {
      return this.JY.contains(var1);
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

   public qz HF(long var1) {
      qz var3 = (qz)this.lm.get(var1);
      if (var3 == null) {
         var3 = qz.q;
      }

      return var3;
   }

   public abn LK(long var1) {
      abn var3 = (abn)this.zz.get(var1);
      if (var3 == null) {
         var3 = abn.q;
      }

      return var3;
   }

   @Override
   protected void xK() {
      super.xK();

      for (Entry var2 : this.gO.entrySet()) {
         ((axo)var2.getValue()).dispose(false);
      }
   }

   @Override
   public void Dw() {
      super.Dw();
      this.Dw = null;
      this.Uv = null;
      this.oW = null;
      this.gO = null;
      this.RF = null;
      this.gP = null;
      this.za = null;
      this.lm = null;
      this.zz = null;
      this.JY = null;
      this.HF = null;
      this.LK = null;
      this.io = null;
      this.qa = null;
   }
}
