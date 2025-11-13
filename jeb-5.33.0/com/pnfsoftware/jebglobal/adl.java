package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.output.code.CodeLine;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.COutputSink;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.CUtil;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.CVisitResults;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICBlock;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICElement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICGlobalContext;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICVisitor;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.simulator.CEnvironment;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.simulator.CMethodState;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.simulator.CSimulationException;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;

@Ser
public abstract class adl implements ICElement {
   @SerId(1)
   private ICGlobalContext pC;
   @SerId(2)
   private LinkedHashSet A;
   @SerId(3)
   private volatile Map kS;

   public void pC(ICGlobalContext var1) {
      this.pC = var1;
   }

   public ICGlobalContext getGlobalContext() {
      return this.pC;
   }

   public void b_() {
      if (this.A != null) {
         this.A.clear();
         this.A = null;
      }
   }

   @Override
   public Long getPhysicalOffset() {
      return this.A != null && !this.A.isEmpty() ? (Long)this.A.iterator().next() : null;
   }

   @Override
   public Collection getPhysicalOffsets() {
      return (Collection)(this.A == null ? Collections.emptyList() : Collections.unmodifiableCollection(this.A));
   }

   protected void pC(adl var1) {
      var1.pC = this.pC;
      var1.A = this.A == null ? null : new LinkedHashSet(this.A);
   }

   @Override
   public void setPhysicalOffsets(Collection var1) {
      this.A = new LinkedHashSet(var1);
   }

   @Override
   public void addPhysicalOffset(Long var1) {
      if (var1 != null) {
         if (this.A == null) {
            this.A = new LinkedHashSet();
         }

         this.A.add(var1);
      }
   }

   @Override
   public void addPhysicalOffsets(Collection var1) {
      if (this.A == null) {
         this.A = new LinkedHashSet();
      }

      for (Long var3 : var1) {
         if (var3 != null) {
            this.A.add(var3);
         }
      }
   }

   @Override
   public List getSubElements() {
      return afm.pC();
   }

   @Override
   public boolean replaceSubElement(ICElement var1, ICElement var2) {
      return false;
   }

   protected void pC(COutputSink var1) {
      var1.recordObjectLocation(this, true, var1.astDepth());
      var1.astPush(this);
   }

   protected void A(COutputSink var1) {
      var1.astPop();
      var1.recordObjectLocation(this, false, var1.astDepth());
   }

   @Override
   public boolean visitDepthPost(ICVisitor var1) {
      return this.visitDepthPost(var1, null, null);
   }

   @Override
   public boolean visitDepthPost(ICVisitor var1, ICElement var2) {
      return this.visitDepthPost(var1, var2, null);
   }

   @Override
   public boolean visitDepthPost(ICVisitor var1, ICElement var2, CVisitResults var3) {
      if (var3 == null) {
         var3 = new CVisitResults();
      }

      pC(var1, this, var2, var3);
      return var3.isVisitedSuccessfully();
   }

   private static void pC(ICVisitor var0, ICElement var1, ICElement var2, CVisitResults var3) {
      if (!(var1 instanceof ICBlock) || var3 == null || (var3.getFlags() & 256) == 0) {
         List var4 = var1.getSubElements();
         var3.pushParent(var1);

         for (ICElement var6 : var4) {
            if (!CUtil.isClassMethodField(var6)) {
               pC(var0, var6, var1, var3);
               if (var3.isInterruptedVisit()) {
                  return;
               }
            }
         }

         var3.popParent();
         var3.setReplacedNode(var1);
         var0.process(var1, var2, var3);
      }
   }

   @Override
   public boolean visitDepthPre(ICVisitor var1) {
      return this.visitDepthPre(var1, null, null);
   }

   @Override
   public boolean visitDepthPre(ICVisitor var1, ICElement var2) {
      return this.visitDepthPre(var1, var2, null);
   }

   @Override
   public boolean visitDepthPre(ICVisitor var1, ICElement var2, CVisitResults var3) {
      if (var3 == null) {
         var3 = new CVisitResults();
      }

      A(var1, this, var2, var3);
      return var3.isVisitedSuccessfully();
   }

   private static void A(ICVisitor var0, ICElement var1, ICElement var2, CVisitResults var3) {
      var3.currentNode = var1;
      boolean var4 = false;
      if (var1 instanceof ICBlock && var3 != null && (var3.getFlags() & 256) != 0) {
         var4 = true;
      }

      if (!var4) {
         var0.process(var1, var2, var3);
      }

      if (!var3.isInterruptedVisit()) {
         if (var3.skipVisitingChildren) {
            var3.skipVisitingChildren = false;
         } else {
            var1 = (ICElement)var3.currentNode;
            var3.pushParent(var1);
            int var5 = 0;

            for (ICElement var7 : var1.getSubElements()) {
               var3.visitedChildPosition = var5;
               A(var0, var7, var1, var3);
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
   public Long evaluate(CMethodState var1, CEnvironment var2) {
      throw new CSimulationException(Strings.ff("TBI: simulation (%s)", this.getClass().toString()));
   }

   private Map pC(boolean var1) {
      if (this.kS == null) {
         if (!var1) {
            return Collections.emptyMap();
         }

         synchronized (this) {
            if (this.kS == null) {
               LinkedHashMap var3 = new LinkedHashMap();
               this.kS = var3;
            }
         }
      }

      return this.kS;
   }

   @Override
   public void setData(String var1, Object var2) {
      if (var2 == null) {
         if (this.kS != null) {
            this.pC(true).remove(var1);
         }
      } else {
         this.pC(true).put(var1, var2);
      }
   }

   @Override
   public Object getData(Object var1) {
      return this.pC(false).get(var1);
   }

   @Override
   public final String format() {
      COutputSink var1 = new COutputSink(0L);
      this.generate(var1);
      StringBuilder var2 = new StringBuilder();

      for (CodeLine var4 : var1.getLines()) {
         var2.append(var4.getText()).append('\n');
      }

      var2.append(var1.getCurrentLine().getText());
      return var2.toString();
   }

   @Override
   public String toString() {
      return Strings.ff("%s", this.getElementType());
   }
}
