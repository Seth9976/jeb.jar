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
public abstract class bim implements IJavaElement {
   protected static final ILogger pC = GlobalLog.getLogger(bim.class);
   @SerId(1)
   private volatile Map A;
   @SerId(2)
   private int kS = -1;
   @SerId(3)
   private IDecompilerUnit wS;
   @SerId(4)
   private int UT;
   @SerId(5)
   private Integer E;

   @SerCustomInitPostGraph
   private void A() {
      if (this.A != null) {
         Integer var1 = (Integer)this.A.get("__$INTERNAL$TAG_Flags");
         if (var1 != null) {
            this.UT = var1 >> 4;
            this.A.remove("__$INTERNAL$TAG_Flags");
            if (this.A.isEmpty()) {
               this.A = null;
            }
         }
      }
   }

   public void pC(IDecompilerUnit var1) {
      this.wS = var1;
   }

   public IDecompilerUnit d_() {
      return this.wS;
   }

   public void pC(int var1, int var2) {
      this.E = var1;
      this.kS = var2;
   }

   @Override
   public boolean hasPhysicalMethodIndex() {
      return this.E != null && this.E >= 0;
   }

   @Override
   public void setPhysicalMethodIndex(int var1) {
      if (var1 < 0) {
         this.E = null;
      } else {
         this.E = var1;
      }
   }

   @Override
   public int getPhysicalMethodIndex() {
      return this.E == null ? -1 : this.E;
   }

   @Override
   public void setPhysicalOffset(int var1) {
      this.kS = var1;
   }

   @Override
   public boolean hasPhysicalOffset() {
      return this.kS != -1;
   }

   @Override
   public int getPhysicalOffset() {
      return this.kS;
   }

   @Override
   public void collectAllPhysicalOffsets(Collection var1) {
      if (this.kS >= 0) {
         var1.add(this.kS);
      }

      for (IJavaElement var3 : this.getSubElements()) {
         ((bim)var3).collectAllPhysicalOffsets(var1);
      }
   }

   protected void pC(bim var1) {
      var1.E = this.E;
      var1.kS = this.kS;
      var1.wS = this.wS;
   }

   @Override
   public boolean canCauseException() {
      return false;
   }

   protected static List pC(List var0) {
      return bhu.pC(var0);
   }

   protected List A(List var1) {
      return bhu.A(var1);
   }

   protected List kS(List var1) {
      return bhu.kS(var1);
   }

   protected IJavaElement pC(JavaOutputSink var1) {
      this.kS(var1);
      var1.recordObjectLocation(this, true, var1.astDepth());
      var1.recordCurrentCoordinates(
         new InstructionCoordinates(this.getPhysicalMethodIndex() >= 0 ? this.getPhysicalMethodIndex() : var1.getCurrentMethodIndex(), this.getPhysicalOffset())
      );
      return var1.astPush(this);
   }

   protected void A(JavaOutputSink var1) {
      var1.astPop();
      var1.unrecordCurrentCoordinates();
      var1.recordObjectLocation(this, false, var1.astDepth());
   }

   private Map pC(boolean var1) {
      if (this.A == null) {
         if (!var1) {
            return Collections.emptyMap();
         }

         synchronized (this) {
            if (this.A == null) {
               LinkedHashMap var3 = new LinkedHashMap();
               this.A = var3;
            }
         }
      }

      return this.A;
   }

   @Override
   public Map getTags() {
      if (this.A == null) {
         return Collections.emptyMap();
      } else {
         LinkedHashMap var1 = new LinkedHashMap();

         for (Entry var3 : this.A.entrySet()) {
            if (var3.getKey() == null || !((String)var3.getKey()).startsWith("__$INTERNAL$TAG_")) {
               var1.put((String)var3.getKey(), var3.getValue());
            }
         }

         return var1;
      }
   }

   public Object pC(String var1) {
      return this.getTags().get(var1);
   }

   @Override
   public Object addTag(String var1, Object var2) {
      return this.pC(true).put(var1, var2);
   }

   @Override
   public Object removeTag(String var1) {
      return this.pC(true).remove(var1);
   }

   private void kS(JavaOutputSink var1) {
      for (Entry var3 : this.getTags().entrySet()) {
         var1.markCurrentPosition((String)var3.getKey(), var3.getValue());
      }
   }

   @Override
   public void setData(String var1, Object var2) {
      if (var2 == null) {
         if (this.A != null) {
            this.pC(true).remove("__$EXTERNAL$DATA_" + var1);
         }
      } else {
         this.pC(true).put("__$EXTERNAL$DATA_" + var1, var2);
      }
   }

   @Override
   public Object getData(Object var1) {
      return this.pC(false).get("__$EXTERNAL$DATA_" + var1);
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

      pC(var1, this, var2, var3);
      return var3.isVisitedSuccessfully();
   }

   private static void pC(IJVisitor var0, IJavaElement var1, IJavaElement var2, JVisitResults var3) {
      var3.currentNode = var1;
      boolean var4 = var3.skipAssignmentDestination && var2 instanceof biq && var3.visitedChildPosition == 0;
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
               pC(var0, var7, var1, var3);
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

      pC(var1, this, var2, var3, var4);
      return var3.isVisitedSuccessfully();
   }

   private static void pC(IJVisitor var0, IJavaElement var1, IJavaElement var2, JVisitResults var3, boolean var4) {
      var3.pushParent(var1);
      List var5;
      if (var1 instanceof IJavaCompound && var4) {
         var5 = ((IJavaCompound)var1).getSubElements(true);
      } else {
         var5 = var1.getSubElements();
      }

      for (IJavaElement var7 : var5) {
         if (var3.parents == null || !var3.parents.contains(var7)) {
            pC(var0, var7, var1, var3, var4);
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
      this.UT = var1;
   }

   @Override
   public int getFlags() {
      return this.UT;
   }

   @Override
   public void addFlags(int var1) {
      this.UT |= var1;
   }

   @Override
   public void removeFlags(int var1) {
      this.UT &= ~var1;
   }

   @Override
   public boolean hasFlags(int var1) {
      return (this.UT & var1) == var1;
   }

   @Override
   public boolean isReconArtifact() {
      return this.getReconEnum() != null || this.getReconEnummap() != null || this.getReconAnon() != null || this.getReconLambda() != null;
   }

   @Override
   public void setLambdaRecon(JavaReconLambda var1) {
      this.pC(true).put("__$INTERNAL$TAG_ReconLambda", var1);
   }

   @Override
   public JavaReconLambda getReconLambda() {
      return (JavaReconLambda)this.pC(false).get("__$INTERNAL$TAG_ReconLambda");
   }

   @Override
   public void setReconEnum(JavaReconEnum var1) {
      this.pC(true).put("__$INTERNAL$TAG_ReconEnum", var1);
   }

   @Override
   public JavaReconEnum getReconEnum() {
      return (JavaReconEnum)this.pC(false).get("__$INTERNAL$TAG_ReconEnum");
   }

   @Override
   public void setReconEnummap(JavaReconEnummap var1) {
      this.pC(true).put("__$INTERNAL$TAG_ReconEnummap", var1);
   }

   @Override
   public JavaReconEnummap getReconEnummap() {
      return (JavaReconEnummap)this.pC(false).get("__$INTERNAL$TAG_ReconEnummap");
   }

   @Override
   public void setReconAnon(JavaReconAnon var1) {
      this.pC(true).put("__$INTERNAL$TAG_ReconAnon", var1);
   }

   @Override
   public JavaReconAnon getReconAnon() {
      return (JavaReconAnon)this.pC(false).get("__$INTERNAL$TAG_ReconAnon");
   }

   @Override
   public void setOrigin(String var1) {
      this.addTag("origin", var1);
   }

   @Override
   public String getOrigin() {
      return (String)this.pC("origin");
   }
}
