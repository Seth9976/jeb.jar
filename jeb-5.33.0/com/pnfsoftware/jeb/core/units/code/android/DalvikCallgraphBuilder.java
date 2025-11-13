package com.pnfsoftware.jeb.core.units.code.android;

import com.pnfsoftware.jeb.core.output.code.coordinates.ICodeCoordinates;
import com.pnfsoftware.jeb.core.output.code.coordinates.InstructionCoordinates;
import com.pnfsoftware.jeb.core.output.code.coordinates.MethodCoordinates;
import com.pnfsoftware.jeb.core.units.code.ICodeClass;
import com.pnfsoftware.jeb.core.units.code.ICodeItem;
import com.pnfsoftware.jeb.core.units.code.ICodeMethod;
import com.pnfsoftware.jeb.core.units.code.IInstruction;
import com.pnfsoftware.jeb.util.collect.MultiMap;
import com.pnfsoftware.jeb.util.collect.WeakIdentityHashMap;
import com.pnfsoftware.jeb.util.collect.WeakValueMap;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.graph.Digraph;
import com.pnfsoftware.jeb.util.graph.IAddressableDigraphBuilder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class DalvikCallgraphBuilder implements IAddressableDigraphBuilder {
   private IDexUnit unit;
   private Digraph model;
   private WeakValueMap vertexIdToMethodObject;
   private WeakIdentityHashMap methodObjectToVertexId;
   private String rawfilter;
   private int fltCount;
   private Set wlFltFull = new HashSet();
   private List wlFltStart = new ArrayList();
   private Set blFltFull = new HashSet();
   private List blFltStart = new ArrayList();

   public DalvikCallgraphBuilder(IDexUnit var1) {
      this.unit = var1;
   }

   @Override
   public Digraph buildModel() {
      MultiMap var1 = new MultiMap();
      HashMap var2 = new HashMap();
      HashMap var3 = new HashMap();
      TreeMap var4 = new TreeMap();

      for (ICodeMethod var6 : this.unit.getMethods()) {
         if (var6.isInternal() && this.filter(var6)) {
            var4.put(var6.getIndex(), var6);
         }
      }

      for (ICodeClass var20 : this.unit.getClasses()) {
         if ((var20.getGenericFlags() & 1048576) == 0 && this.filter(var20)) {
            int var7 = var20.getClassType().getIndex();

            for (ICodeMethod var9 : var20.getMethods()) {
               int var10 = var9.getIndex();
               var1.put(var7, var10);
               var2.put(var10, var7);
               List var11 = var9.getInstructions();
               if (var11 != null) {
                  for (IInstruction var13 : var11) {
                     String var14 = var13.format(null);
                     int var15 = extractMethodIndex(var14);
                     if (var15 >= 0 && var4.containsKey(var15)) {
                        Object var16 = (Set)var3.get(var10);
                        if (var16 == null) {
                           var16 = new TreeSet();
                           var3.put(var10, var16);
                        }

                        var16.add(var15);
                     }
                  }
               }
            }
         }
      }

      this.model = new Digraph();
      this.vertexIdToMethodObject = new WeakValueMap();
      this.methodObjectToVertexId = new WeakIdentityHashMap();

      for (ICodeMethod var21 : var4.values()) {
         String var23 = var21.getClassType().getName(true) + "." + var21.getName(true);
         int var25 = var21.getInstructions() == null ? 0 : var21.getInstructions().size();
         this.model.v(var21.getIndex(), (double)var25, var23);
         this.vertexIdToMethodObject.put(var21.getIndex(), var21);
         this.methodObjectToVertexId.put(var21, var21.getIndex());
      }

      for (int var22 : var3.keySet()) {
         for (int var26 : (Set)var3.get(var22)) {
            this.model.e(var22, var26);
         }
      }

      return this.model;
   }

   private static int extractMethodIndex(String var0) {
      if (var0.startsWith("invoke")) {
         int var1 = var0.indexOf("method@");
         if (var1 >= 0) {
            var1 += 7;
            int var2 = var0.indexOf(",", var1);
            if (var2 < 0) {
               var2 = var0.length();
            }

            return Integer.parseInt(var0.substring(var1, var2));
         }
      }

      return -1;
   }

   @Override
   public String getAddressForVertexId(int var1) {
      ICodeMethod var2 = this.vertexIdToMethodObject == null ? null : (ICodeMethod)this.vertexIdToMethodObject.get(var1);
      return var2 == null ? null : var2.getAddress();
   }

   @Override
   public Integer getVertexIdForAddress(String var1) {
      ICodeCoordinates var2 = this.unit.getCodeCoordinatesFromAddress(var1);
      Integer var3;
      if (var2 instanceof InstructionCoordinates) {
         var3 = ((InstructionCoordinates)var2).getMethodId();
      } else {
         if (!(var2 instanceof MethodCoordinates)) {
            return null;
         }

         var3 = ((MethodCoordinates)var2).getMethodId();
      }

      ICodeMethod var4 = (ICodeMethod)this.unit.getMethods().get(var3);
      return var4 == null ? null : (Integer)this.methodObjectToVertexId.get(var4);
   }

   public String getFilter() {
      return this.rawfilter;
   }

   public void setFilter(String var1) {
      this.rawfilter = var1;
      this.wlFltStart.clear();
      this.blFltStart.clear();
      if (this.rawfilter != null) {
         this.fltCount = 0;

         for (String var5 : Strings.splitLines(this.rawfilter)) {
            var5 = Strings.trim(var5);
            if (!var5.isEmpty() && !var5.startsWith("#")) {
               boolean var6 = false;
               if (var5.startsWith("-")) {
                  var5 = var5.substring(1);
                  if (var5.isEmpty()) {
                     continue;
                  }

                  var6 = true;
               }

               if (var5.endsWith("*")) {
                  var5 = var5.substring(0, var5.length() - 1);
                  if (var6) {
                     this.blFltStart.add(var5);
                  } else {
                     this.wlFltStart.add(var5);
                  }
               } else if (var6) {
                  this.blFltFull.add(var5);
               } else {
                  this.wlFltFull.add(var5);
               }

               this.fltCount++;
            }
         }
      }
   }

   private boolean filter(ICodeItem var1) {
      if (this.fltCount == 0) {
         return true;
      } else {
         String var2 = var1.getSignature(true);
         if (var2 == null) {
            return false;
         } else {
            int var3 = var2.indexOf(";");
            if (var3 < 2) {
               return false;
            } else {
               String var4 = var2.substring(1, var3).replace('/', '.');
               if ((!this.wlFltFull.isEmpty() || !this.wlFltStart.isEmpty()) && !this.wlFltFull.contains(var4)) {
                  boolean var5 = false;

                  for (String var7 : this.wlFltStart) {
                     if (var4.startsWith(var7)) {
                        var5 = true;
                        break;
                     }
                  }

                  if (!var5) {
                     return false;
                  }
               }

               if (this.blFltFull.contains(var4)) {
                  return false;
               } else {
                  for (String var9 : this.blFltStart) {
                     if (var4.startsWith(var9)) {
                        return false;
                     }
                  }

                  return true;
               }
            }
         }
      }
   }
}
