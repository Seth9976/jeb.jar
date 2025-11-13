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
public class afm extends afh implements ICCall {
   public static final int RF = 1;
   public static final int xK = 2;
   public static final int Dw = 4;
   @SerId(1)
   int Uv;
   @SerId(2)
   List oW;
   @SerId(3)
   String gO;
   @SerId(4)
   ICExpression nf;
   @SerId(5)
   List gP;

   afm(String var1, List var2) {
      this.gO = var1;
      this.oW = new ArrayList();
      this.oW.addAll(var2);
      this.gP = new ArrayList();
   }

   afm(ICExpression var1, List var2, List var3) {
      this.nf = var1;
      this.oW = new ArrayList();
      this.oW.addAll(var2);
      this.gP = new ArrayList();
      if (var3 != null) {
         this.gP.addAll(var3);
      }
   }

   private afm(int var1, List var2, String var3, ICExpression var4, List var5) {
      this.Uv = var1;
      this.oW = (List)var2.stream().map(var0 -> var0.duplicate()).collect(Collectors.toCollection(ArrayList::new));
      this.gO = var3;
      this.nf = var4 == null ? null : var4.duplicate();
      this.gP = var5 == null ? null : new ArrayList(var5);
   }

   @Override
   public String getMethodAddress() {
      return this.gO;
   }

   @Override
   public ICMethod getMethod() {
      return afc.xK(this, this.gO);
   }

   @Override
   public ICExpression getCallsite() {
      return this.nf;
   }

   @Override
   public List getCandidateMethodAddresses() {
      return this.gP == null ? null : Collections.unmodifiableList(this.gP);
   }

   @Override
   public List getCandidateMethods() {
      if (this.gP == null) {
         return null;
      } else {
         ArrayList var1 = new ArrayList(this.gP.size());
         this.gP.forEach(var2 -> var1.add(afc.xK(this, var2)));
         return var1;
      }
   }

   @Override
   public boolean isStatic() {
      return this.gO != null;
   }

   public void q(ICMethod var1, boolean var2) {
      if (var1 == null) {
         throw new IllegalArgumentException();
      } else {
         this.gO = var1.getAddress();
         this.q(var2);
      }
   }

   public void q(boolean var1) {
      this.Uv = Flags.set(this.Uv, 1, var1);
   }

   @Override
   public boolean isSuperCall() {
      return Flags.has(this.Uv, 1);
   }

   public void RF(boolean var1) {
      this.Uv = Flags.set(this.Uv, 2, var1);
   }

   @Override
   public boolean isBadCall() {
      return Flags.has(this.Uv, 2);
   }

   public void xK(boolean var1) {
      this.Uv = Flags.set(this.Uv, 4, var1);
   }

   @Override
   public boolean isNonReturningCall() {
      return Flags.has(this.Uv, 4);
   }

   @Override
   public int getArgumentCount() {
      return this.oW.size();
   }

   @Override
   public List getArguments() {
      return this.oW;
   }

   public void q(ICExpression var1) {
      if (var1 == null) {
         throw new IllegalArgumentException();
      } else {
         this.oW.add(var1);
      }
   }

   public void q(int var1, ICExpression var2) {
      if (var2 == null) {
         throw new IllegalArgumentException();
      } else {
         this.oW.add(var1, var2);
      }
   }

   public void RF(int var1, ICExpression var2) {
      if (var2 == null) {
         throw new IllegalArgumentException();
      } else {
         this.oW.set(var1, var2);
      }
   }

   public ICExpression q(int var1) {
      return (ICExpression)this.oW.remove(var1);
   }

   @Override
   public List getSubElements() {
      List var1 = ahf.q(this.nf);
      ahf.q(var1, this.oW);
      return var1;
   }

   @Override
   public boolean replaceSubElement(ICElement var1, ICElement var2) {
      if (this.nf == var1) {
         if (var2 != null && !(var2 instanceof ICExpression)) {
            return false;
         } else {
            this.nf = (ICExpression)var2;
            return true;
         }
      } else {
         for (int var3 = 0; var3 < this.oW.size(); var3++) {
            if (this.oW.get(var3) == var1) {
               if (!(var2 instanceof ICExpression)) {
                  return false;
               }

               this.oW.set(var3, (ICExpression)var2);
               return true;
            }
         }

         return false;
      }
   }

   private void q(String var1, COutputSink var2) {
      String var3 = var1;
      long var4 = 0L;
      ICMethod var6 = afc.xK(this, var1);
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

   private boolean xK(COutputSink var1) {
      if (this.isStatic()) {
         return false;
      } else {
         long var2;
         if (this.nf instanceof afs) {
            var2 = ((afs)this.nf).getValueAsLong();
         } else {
            if (!(this.nf instanceof aft)) {
               return false;
            }

            var2 = ((aft)this.nf).getValueAsLong();
         }

         if (this.gP.size() > 0) {
            this.q((String)this.gP.get(0), var1);
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
      this.q(var1);
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
         this.q(this.gO, var1);
      } else {
         var2 = this.xK(var1);
         if (!var2) {
            this.nf.generate(var1);
         }
      }

      byte var3 = 0;
      if (this.gP != null && !this.gP.isEmpty() && !var2) {
         var1.brace();
         int var4 = 0;

         for (String var6 : this.gP) {
            if (var4 >= 1) {
               var1.append("|");
            }

            this.q(var6, var1);
            var4++;
         }

         var1.braceClose();
      }

      var1.paren();

      for (int var7 = var3; var7 < this.oW.size(); var7++) {
         if (var7 > var3) {
            var1.append(", ");
         }

         ((ICExpression)this.oW.get(var7)).generate(var1);
      }

      var1.parenClose();
      this.RF(var1);
   }

   @Override
   public CElementType getElementType() {
      return CElementType.Call;
   }

   @Override
   public int hashCode() {
      int var1 = 1;
      var1 = 31 * var1 + (this.oW == null ? 0 : this.oW.hashCode());
      var1 = 31 * var1 + (this.nf == null ? 0 : this.nf.hashCode());
      var1 = 31 * var1 + (this.gP == null ? 0 : this.gP.hashCode());
      var1 = 31 * var1 + (this.gO == null ? 0 : this.gO.hashCode());
      return 31 * var1 + this.Uv;
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
         afm var2 = (afm)var1;
         if (this.gO == null) {
            if (var2.gO != null) {
               return false;
            }
         } else if (!this.gO.equals(var2.gO)) {
            return false;
         }

         if (this.oW == null) {
            if (var2.oW != null) {
               return false;
            }
         } else if (!this.oW.equals(var2.oW)) {
            return false;
         }

         if (this.nf == null) {
            if (var2.nf != null) {
               return false;
            }
         } else if (!this.nf.equals(var2.nf)) {
            return false;
         }

         if (this.gP == null) {
            if (var2.gP != null) {
               return false;
            }
         } else if (!this.gP.equals(var2.gP)) {
            return false;
         }

         return this.Uv == var2.Uv;
      }
   }

   public afm RF() {
      afm var1 = new afm(this.Uv, this.oW, this.gO, this.nf, this.gP);
      super.q(var1);
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
         for (ICExpression var5 : this.oW) {
            if (var5 instanceof agh) {
               var1.setValue(var5, var2.getPassedParameterValue((agh)var5), var2);
            }
         }
      }

      return var3;
   }

   @Override
   public String toString() {
      return this.getMethodAddress() + "(" + Strings.join(", ", this.oW) + ")";
   }
}
