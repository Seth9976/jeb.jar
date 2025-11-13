package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.output.code.coordinates.InstructionCoordinates;
import com.pnfsoftware.jeb.core.units.code.IDecompilerUnit;
import com.pnfsoftware.jeb.core.units.code.java.IJVisitor;
import com.pnfsoftware.jeb.core.units.code.java.IJavaCompound;
import com.pnfsoftware.jeb.core.units.code.java.IJavaElement;
import com.pnfsoftware.jeb.core.units.code.java.JVisitResults;
import com.pnfsoftware.jeb.core.units.code.java.JavaOutputSink;
import com.pnfsoftware.jeb.core.units.code.java.JavaReconAnon;
import com.pnfsoftware.jeb.core.units.code.java.JavaReconEnum;
import com.pnfsoftware.jeb.core.units.code.java.JavaReconEnummap;
import com.pnfsoftware.jeb.core.units.code.java.JavaReconLambda;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerCustomInitPostGraph;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

@Ser
public abstract class bmj implements IJavaElement {
   protected static final ILogger q = GlobalLog.getLogger(bmj.class);
   public static final String RF = "__$INTERNAL$TAG_";
   public static final String xK = "__$EXTERNAL$DATA_";
   @SerId(1)
   private volatile Map Dw;
   @SerId(2)
   private int Uv = -1;
   @SerId(3)
   private IDecompilerUnit oW;
   @SerId(4)
   private int gO;
   @SerId(5)
   private Integer nf;
   @Deprecated
   private static final String gP = "__$INTERNAL$TAG_Flags";
   private static final String za = "__$INTERNAL$TAG_ReconLambda";
   private static final String lm = "__$INTERNAL$TAG_ReconEnum";
   private static final String zz = "__$INTERNAL$TAG_ReconEnummap";
   private static final String JY = "__$INTERNAL$TAG_ReconAnon";
   private static final String HF = "origin";

   @SerCustomInitPostGraph
   private void xK() {
      if (this.Dw != null) {
         Integer var1 = (Integer)this.Dw.get("__$INTERNAL$TAG_Flags");
         if (var1 != null) {
            this.gO = var1 >> 4;
            this.Dw.remove("__$INTERNAL$TAG_Flags");
            if (this.Dw.isEmpty()) {
               this.Dw = null;
            }
         }
      }
   }

   public void q(IDecompilerUnit var1) {
      this.oW = var1;
   }

   public IDecompilerUnit d_() {
      return this.oW;
   }

   public void q(int var1, int var2) {
      this.nf = var1;
      this.Uv = var2;
   }

   @Override
   public boolean hasPhysicalMethodIndex() {
      return this.nf != null && this.nf >= 0;
   }

   @Override
   public void setPhysicalMethodIndex(int var1) {
      if (var1 < 0) {
         this.nf = null;
      } else {
         this.nf = var1;
      }
   }

   @Override
   public int getPhysicalMethodIndex() {
      return this.nf == null ? -1 : this.nf;
   }

   @Override
   public void setPhysicalOffset(int var1) {
      this.Uv = var1;
   }

   @Override
   public boolean hasPhysicalOffset() {
      return this.Uv != -1;
   }

   @Override
   public int getPhysicalOffset() {
      return this.Uv;
   }

   @Override
   public void collectAllPhysicalOffsets(Collection var1) {
      if (this.Uv >= 0) {
         var1.add(this.Uv);
      }

      for (IJavaElement var3 : this.getSubElements()) {
         ((bmj)var3).collectAllPhysicalOffsets(var1);
      }
   }

   public int RF() {
      if (this.Uv >= 0) {
         return this.Uv;
      } else {
         for (IJavaElement var2 : this.getSubElements()) {
            int var3 = ((bmj)var2).RF();
            if (var3 >= 0) {
               return var3;
            }
         }

         return -1;
      }
   }

   protected void q(bmj var1) {
      var1.nf = this.nf;
      var1.Uv = this.Uv;
      var1.oW = this.oW;
   }

   @Override
   public boolean canCauseException() {
      return false;
   }

   protected static List q(List var0) {
      return blr.q(var0);
   }

   protected List RF(List var1) {
      return blr.RF(var1);
   }

   protected List xK(List var1) {
      return blr.xK(var1);
   }

   protected IJavaElement q(JavaOutputSink var1) {
      this.xK(var1);
      var1.recordObjectLocation(this, true, var1.astDepth());
      var1.recordCurrentCoordinates(
         new InstructionCoordinates(this.getPhysicalMethodIndex() >= 0 ? this.getPhysicalMethodIndex() : var1.getCurrentMethodIndex(), this.getPhysicalOffset())
      );
      return var1.astPush(this);
   }

   protected void RF(JavaOutputSink var1) {
      var1.astPop();
      var1.unrecordCurrentCoordinates();
      var1.recordObjectLocation(this, false, var1.astDepth());
   }

   private Map q(boolean var1) {
      if (this.Dw == null) {
         if (!var1) {
            return Collections.emptyMap();
         }

         synchronized (this) {
            if (this.Dw == null) {
               LinkedHashMap var3 = new LinkedHashMap();
               this.Dw = var3;
            }
         }
      }

      return this.Dw;
   }

   @Override
   public Map getTags() {
      if (this.Dw == null) {
         return Collections.emptyMap();
      } else {
         LinkedHashMap var1 = new LinkedHashMap();

         for (Entry var3 : this.Dw.entrySet()) {
            if (var3.getKey() == null || !((String)var3.getKey()).startsWith("__$INTERNAL$TAG_")) {
               var1.put((String)var3.getKey(), var3.getValue());
            }
         }

         return var1;
      }
   }

   public Object q(String var1) {
      return this.getTags().get(var1);
   }

   @Override
   public Object addTag(String var1, Object var2) {
      return this.q(true).put(var1, var2);
   }

   @Override
   public Object removeTag(String var1) {
      return this.q(true).remove(var1);
   }

   private void xK(JavaOutputSink var1) {
      for (Entry var3 : this.getTags().entrySet()) {
         var1.markCurrentPosition((String)var3.getKey(), var3.getValue());
      }
   }

   @Override
   public void setData(String var1, Object var2) {
      if (var2 == null) {
         if (this.Dw != null) {
            this.q(true).remove("__$EXTERNAL$DATA_" + var1);
         }
      } else {
         this.q(true).put("__$EXTERNAL$DATA_" + var1, var2);
      }
   }

   @Override
   public Object getData(Object var1) {
      return this.q(false).get("__$EXTERNAL$DATA_" + var1);
   }

   @Override
   public abstract String toString();

   @Override
   public String toShortString() {
      return this.toString();
   }

   @Override
   public boolean visitDepthPre(IJVisitor var1) {
      return this.visitDepthPre(var1, null, null);
   }

   @Override
   public boolean visitDepthPre(IJVisitor var1, IJavaElement var2) {
      return this.visitDepthPre(var1, var2, null);
   }

   @Override
   public boolean visitDepthPre(IJVisitor var1, IJavaElement var2, JVisitResults var3) {
      if (var3 == null) {
         var3 = new JVisitResults();
      }

      q(var1, this, var2, var3);
      return var3.isVisitedSuccessfully();
   }

   private static void q(IJVisitor var0, IJavaElement var1, IJavaElement var2, JVisitResults var3) {
      var3.currentNode = var1;
      boolean var4 = var3.skipAssignmentDestination && var2 instanceof bmn && var3.visitedChildPosition == 0;
      if (!var4) {
         var0.process(var1, var2, var3);
      }

      if (!var3.isInterruptedVisit()) {
         if (var3.skipVisitingChildren) {
            var3.skipVisitingChildren = false;
         } else {
            var1 = (IJavaElement)var3.currentNode;
            var3.pushParent(var1);
            int var5 = 0;

            for (IJavaElement var7 : var1.getSubElements()) {
               var3.visitedChildPosition = var5;
               q(var0, var7, var1, var3);
               if (var3.isInterruptedVisit()) {
                  return;
               }

               var5++;
            }

            var3.popParent();
         }
      }
   }

   @Override
   public boolean visitDepthPost(IJVisitor var1) {
      return this.visitDepthPost(var1, null, null);
   }

   @Override
   public boolean visitDepthPost(IJVisitor var1, IJavaElement var2) {
      return this.visitDepthPost(var1, var2, null);
   }

   @Override
   public boolean visitDepthPost(IJVisitor var1, IJavaElement var2, JVisitResults var3) {
      return this.visitDepthPost(var1, var2, var3, false);
   }

   @Override
   public boolean visitDepthPost(IJVisitor var1, IJavaElement var2, JVisitResults var3, boolean var4) {
      if (var3 == null) {
         var3 = new JVisitResults(1);
      }

      q(var1, this, var2, var3, var4);
      return var3.isVisitedSuccessfully();
   }

   private static void q(IJVisitor var0, IJavaElement var1, IJavaElement var2, JVisitResults var3, boolean var4) {
      var3.pushParent(var1);
      List var5;
      if (var1 instanceof IJavaCompound && var4) {
         var5 = ((IJavaCompound)var1).getSubElements(true);
      } else {
         var5 = var1.getSubElements();
      }

      for (IJavaElement var7 : var5) {
         if (var3.parents == null || !var3.parents.contains(var7)) {
            q(var0, var7, var1, var3, var4);
            if (var3.isInterruptedVisit()) {
               return;
            }
         }
      }

      var3.popParent();
      var3.setReplacedNode(var1);
      var0.process(var1, var2, var3);
   }

   @Override
   public void setFlags(int var1) {
      this.gO = var1;
   }

   @Override
   public int getFlags() {
      return this.gO;
   }

   @Override
   public void addFlags(int var1) {
      this.gO |= var1;
   }

   @Override
   public void removeFlags(int var1) {
      this.gO &= ~var1;
   }

   @Override
   public boolean hasFlags(int var1) {
      return (this.gO & var1) == var1;
   }

   @Override
   public boolean isReconArtifact() {
      return this.getReconEnum() != null || this.getReconEnummap() != null || this.getReconAnon() != null || this.getReconLambda() != null;
   }

   @Override
   public void setLambdaRecon(JavaReconLambda var1) {
      this.q(true).put("__$INTERNAL$TAG_ReconLambda", var1);
   }

   @Override
   public JavaReconLambda getReconLambda() {
      return (JavaReconLambda)this.q(false).get("__$INTERNAL$TAG_ReconLambda");
   }

   @Override
   public void setReconEnum(JavaReconEnum var1) {
      this.q(true).put("__$INTERNAL$TAG_ReconEnum", var1);
   }

   @Override
   public JavaReconEnum getReconEnum() {
      return (JavaReconEnum)this.q(false).get("__$INTERNAL$TAG_ReconEnum");
   }

   @Override
   public void setReconEnummap(JavaReconEnummap var1) {
      this.q(true).put("__$INTERNAL$TAG_ReconEnummap", var1);
   }

   @Override
   public JavaReconEnummap getReconEnummap() {
      return (JavaReconEnummap)this.q(false).get("__$INTERNAL$TAG_ReconEnummap");
   }

   @Override
   public void setReconAnon(JavaReconAnon var1) {
      this.q(true).put("__$INTERNAL$TAG_ReconAnon", var1);
   }

   @Override
   public JavaReconAnon getReconAnon() {
      return (JavaReconAnon)this.q(false).get("__$INTERNAL$TAG_ReconAnon");
   }

   @Override
   public void setOrigin(String var1) {
      this.addTag("origin", var1);
   }

   @Override
   public String getOrigin() {
      return (String)this.q("origin");
   }
}
