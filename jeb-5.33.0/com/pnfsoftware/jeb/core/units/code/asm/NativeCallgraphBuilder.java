package com.pnfsoftware.jeb.core.units.code.asm;

import com.pnfsoftware.jeb.core.output.code.coordinates.ICodeCoordinates;
import com.pnfsoftware.jeb.core.output.code.coordinates.InstructionCoordinates;
import com.pnfsoftware.jeb.core.output.code.coordinates.MethodCoordinates;
import com.pnfsoftware.jeb.core.output.code.coordinates.NativeCoordinates;
import com.pnfsoftware.jeb.core.units.INativeCodeUnit;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.CallGraphVertex;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.ICallGraph;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeMethodItem;
import com.pnfsoftware.jeb.util.collect.MultiMap;
import com.pnfsoftware.jeb.util.collect.WeakIdentityHashMap;
import com.pnfsoftware.jeb.util.collect.WeakValueMap;
import com.pnfsoftware.jeb.util.graph.Digraph;
import com.pnfsoftware.jeb.util.graph.IAddressableDigraphBuilder;
import java.util.List;

public class NativeCallgraphBuilder implements IAddressableDigraphBuilder {
   private INativeCodeUnit unit;
   private Digraph model;
   private WeakValueMap vertexIdToMethodObject;
   private WeakIdentityHashMap methodObjectToVertexId;

   public NativeCallgraphBuilder(INativeCodeUnit var1) {
      this.unit = var1;
   }

   @Override
   public Digraph buildModel() {
      List var1 = this.unit.getInternalMethods();
      ICallGraph var2 = this.unit.getCodeModel().getCallGraphManager().getGlobalCallGraph();
      MultiMap var3 = new MultiMap();

      for (INativeMethodItem var5 : var1) {
         for (CallGraphVertex var8 : var2.getCallees(var5, false)) {
            if (var8.isInternal()) {
               int var9 = var1.indexOf(var5);
               INativeMethodItem var10 = this.unit.getInternalMethod(var8.getInternalAddress().getAddress(), true);
               if (var10 != null) {
                  int var11 = var1.indexOf(var10);
                  if (!var3.getSafe(var9).contains(var11)) {
                     var3.put(var9, var11);
                  }
               }
            }
         }
      }

      this.model = new Digraph();
      this.vertexIdToMethodObject = new WeakValueMap();
      this.methodObjectToVertexId = new WeakIdentityHashMap();
      int var12 = 0;

      for (INativeMethodItem var15 : var1) {
         int var17 = var15.getData().getCFG().getInstructionCount();
         this.model.v(var12, (double)var17, var15.getName(true));
         this.vertexIdToMethodObject.put(var12, var15);
         this.methodObjectToVertexId.put(var15, var12);
         var12++;
      }

      for (int var16 : var3.keySet()) {
         for (int var19 : var3.getSafe(var16)) {
            this.model.e(var16, var19);
         }
      }

      return this.model;
   }

   @Override
   public String getAddressForVertexId(int var1) {
      INativeMethodItem var2 = this.vertexIdToMethodObject == null ? null : (INativeMethodItem)this.vertexIdToMethodObject.get(var1);
      return var2 == null ? null : var2.getAddress();
   }

   @Override
   public Integer getVertexIdForAddress(String var1) {
      ICodeCoordinates var2 = this.unit.getCodeCoordinatesFromAddress(var1);
      INativeMethodItem var3;
      if (var2 instanceof MethodCoordinates) {
         int var4 = ((MethodCoordinates)var2).getMethodId();
         var3 = this.unit.getMethodByIndex(var4);
      } else if (var2 instanceof InstructionCoordinates) {
         int var6 = ((InstructionCoordinates)var2).getMethodId();
         var3 = this.unit.getMethodByIndex(var6);
      } else {
         if (!(var2 instanceof NativeCoordinates)) {
            return null;
         }

         long var7 = ((NativeCoordinates)var2).getAddress();
         var3 = this.unit.getInternalMethod(var7, false);
      }

      return var3 == null ? null : (Integer)this.methodObjectToVertexId.get(var3);
   }
}
