package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.ICoreContext;
import com.pnfsoftware.jeb.core.JebCoreService;
import com.pnfsoftware.jeb.core.output.ItemClassIdentifiers;
import com.pnfsoftware.jeb.core.output.code.coordinates.MethodCoordinates;
import com.pnfsoftware.jeb.core.output.code.coordinates.NativeCoordinates;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IDynamicContentManager;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.CElementType;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.COutputSink;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICCall;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICElement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICExpression;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICMethod;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.simulator.CEnvironment;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.simulator.CMethodState;
import com.pnfsoftware.jeb.util.base.Flags;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Ser
public class adt extends ado implements ICCall {
   @SerId(1)
   int A;
   @SerId(2)
   List kS;
   @SerId(3)
   String wS;
   @SerId(4)
   ICExpression UT;
   @SerId(5)
   List E;

   adt(String var1, List var2) {
      this.wS = var1;
      this.kS = new ArrayList();
      this.kS.addAll(var2);
      this.E = new ArrayList();
   }

   adt(ICExpression var1, List var2, List var3) {
      this.UT = var1;
      this.kS = new ArrayList();
      this.kS.addAll(var2);
      this.E = new ArrayList();
      if (var3 != null) {
         this.E.addAll(var3);
      }
   }

   private adt(int var1, List var2, String var3, ICExpression var4, List var5) {
      this.A = var1;
      this.kS = (List)var2.stream().map(var0 -> var0.duplicate()).collect(Collectors.toCollection(ArrayList::new));
      this.wS = var3;
      this.UT = var4 == null ? null : var4.duplicate();
      this.E = var5 == null ? null : new ArrayList(var5);
   }

   @Override
   public String getMethodAddress() {
      return this.wS;
   }

   @Override
   public ICMethod getMethod() {
      return adj.A(this, this.wS);
   }

   @Override
   public ICExpression getCallsite() {
      return this.UT;
   }

   @Override
   public List getCandidateMethodAddresses() {
      return this.E == null ? null : Collections.unmodifiableList(this.E);
   }

   @Override
   public List getCandidateMethods() {
      if (this.E == null) {
         return null;
      } else {
         ArrayList var1 = new ArrayList(this.E.size());
         this.E.forEach(var2 -> var1.add(adj.A(this, var2)));
         return var1;
      }
   }

   @Override
   public boolean isStatic() {
      return this.wS != null;
   }

   @Override
   public boolean isSuperCall() {
      return Flags.has(this.A, 1);
   }

   public void pC(boolean var1) {
      this.A = Flags.set(this.A, 2, var1);
   }

   @Override
   public boolean isBadCall() {
      return Flags.has(this.A, 2);
   }

   public void A(boolean var1) {
      this.A = Flags.set(this.A, 4, var1);
   }

   @Override
   public boolean isNonReturningCall() {
      return Flags.has(this.A, 4);
   }

   @Override
   public int getArgumentCount() {
      return this.kS.size();
   }

   @Override
   public List getArguments() {
      return this.kS;
   }

   @Override
   public List getSubElements() {
      List var1 = afm.pC(this.UT);
      afm.pC(var1, this.kS);
      return var1;
   }

   @Override
   public boolean replaceSubElement(ICElement var1, ICElement var2) {
      if (this.UT == var1) {
         if (var2 != null && !(var2 instanceof ICExpression)) {
            return false;
         } else {
            this.UT = (ICExpression)var2;
            return true;
         }
      } else {
         for (int var3 = 0; var3 < this.kS.size(); var3++) {
            if (this.kS.get(var3) == var1) {
               if (!(var2 instanceof ICExpression)) {
                  return false;
               }

               this.kS.set(var3, (ICExpression)var2);
               return true;
            }
         }

         return false;
      }
   }

   private void pC(String var1, COutputSink var2) {
      String var3 = var1;
      long var4 = 0L;
      ICMethod var6 = adj.A(this, var1);
      if (var6 != null) {
         var3 = var6.getName();
         IDynamicContentManager var7 = var2.getDynamicContentManager();
         if (var7 != null) {
            MethodCoordinates var8 = new MethodCoordinates(var6.getIndex());
            var3 = var7.getMethodName(var8);
            var4 = var7.getMethodItemId(var8);
            if (var3 == null) {
               var3 = var6.getName();
               var4 = 0L;
            }
         }
      }

      if (var2.getHideTLNS()) {
         int var9 = var3.lastIndexOf("::");
         if (var9 >= 0) {
            var3 = var3.substring(var9 + 2);
         }
      }

      ICoreContext var10 = JebCoreService.getExistingInstance();
      if (var10 != null && var10.getOptions().isUIClient() && var3.startsWith("→")) {
         var3 = var3.substring("→".length());
      }

      var2.appendAndRecord(var3, ItemClassIdentifiers.METHOD_NAME, var4);
   }

   private boolean kS(COutputSink var1) {
      if (this.isStatic()) {
         return false;
      } else {
         long var2;
         if (this.UT instanceof adz) {
            var2 = ((adz)this.UT).getValueAsLong();
         } else {
            if (!(this.UT instanceof aea)) {
               return false;
            }

            var2 = ((aea)this.UT).getValueAsLong();
         }

         if (this.E.size() > 0) {
            this.pC((String)this.E.get(0), var1);
            return true;
         } else {
            IDynamicContentManager var4 = var1.getDynamicContentManager();
            if (var4 != null) {
               NativeCoordinates var5 = new NativeCoordinates(var2);
               String var6 = var4.getMethodName(var5);
               if (var6 != null) {
                  long var7 = var4.getMethodItemId(var5);
                  var1.appendAndRecord(var6, ItemClassIdentifiers.METHOD_NAME, var7);
                  return true;
               }
            }

            return false;
         }
      }
   }

   @Override
   public void generate(COutputSink var1) {
      this.pC(var1);
      if (this.isNonReturningCall()) {
         var1.appendCommentAuto("/*NO_RETURN*/");
         var1.space();
      }

      if (this.isBadCall()) {
         var1.appendCommentAuto("/*BAD_CALL!*/");
         var1.space();
      }

      boolean var2 = false;
      if (this.isStatic()) {
         this.pC(this.wS, var1);
      } else {
         var2 = this.kS(var1);
         if (!var2) {
            this.UT.generate(var1);
         }
      }

      byte var3 = 0;
      if (this.E != null && !this.E.isEmpty() && !var2) {
         var1.brace();
         int var4 = 0;

         for (String var6 : this.E) {
            if (var4 >= 1) {
               var1.append("|");
            }

            this.pC(var6, var1);
            var4++;
         }

         var1.braceClose();
      }

      var1.paren();

      for (int var7 = var3; var7 < this.kS.size(); var7++) {
         if (var7 > var3) {
            var1.append(", ");
         }

         ((ICExpression)this.kS.get(var7)).generate(var1);
      }

      var1.parenClose();
      this.A(var1);
   }

   @Override
   public CElementType getElementType() {
      return CElementType.Call;
   }

   @Override
   public int hashCode() {
      int var1 = 1;
      var1 = 31 * var1 + (this.kS == null ? 0 : this.kS.hashCode());
      var1 = 31 * var1 + (this.UT == null ? 0 : this.UT.hashCode());
      var1 = 31 * var1 + (this.E == null ? 0 : this.E.hashCode());
      var1 = 31 * var1 + (this.wS == null ? 0 : this.wS.hashCode());
      return 31 * var1 + this.A;
   }

   @Override
   public boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else if (var1 == null) {
         return false;
      } else if (this.getClass() != var1.getClass()) {
         return false;
      } else {
         adt var2 = (adt)var1;
         if (this.wS == null) {
            if (var2.wS != null) {
               return false;
            }
         } else if (!this.wS.equals(var2.wS)) {
            return false;
         }

         if (this.kS == null) {
            if (var2.kS != null) {
               return false;
            }
         } else if (!this.kS.equals(var2.kS)) {
            return false;
         }

         if (this.UT == null) {
            if (var2.UT != null) {
               return false;
            }
         } else if (!this.UT.equals(var2.UT)) {
            return false;
         }

         if (this.E == null) {
            if (var2.E != null) {
               return false;
            }
         } else if (!this.E.equals(var2.E)) {
            return false;
         }

         return this.A == var2.A;
      }
   }

   public adt A() {
      adt var1 = new adt(this.A, this.kS, this.wS, this.UT, this.E);
      super.pC(var1);
      return var1;
   }

   @Override
   public Long evaluate(CMethodState var1, CEnvironment var2) {
      Long.valueOf(0L);
      Long var3;
      if (this.isStatic()) {
         var3 = var2.getRoutineReturnValue(this.getMethod(), true);
      } else {
         var3 = var2.getRoutineDefaultReturnValue();
      }

      if (var1.isMethodParametersSpoiled()) {
         for (ICExpression var5 : this.kS) {
            if (var5 instanceof aeo) {
               var1.setValue(var5, var2.getPassedParameterValue((aeo)var5), var2);
            }
         }
      }

      return var3;
   }

   @Override
   public String toString() {
      return this.getMethodAddress() + "(" + Strings.join(", ", this.kS) + ")";
   }
}
