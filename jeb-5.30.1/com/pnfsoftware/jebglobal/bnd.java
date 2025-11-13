package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.output.code.coordinates.ICodeCoordinates;
import com.pnfsoftware.jeb.core.output.code.coordinates.IdentifierCoordinates;
import com.pnfsoftware.jeb.core.output.code.coordinates.InstructionCoordinates;
import com.pnfsoftware.jeb.core.output.code.coordinates.MethodCoordinates;
import com.pnfsoftware.jeb.core.units.code.java.IJavaDefinition;
import com.pnfsoftware.jeb.core.units.code.java.IJavaIdentifier;
import com.pnfsoftware.jeb.core.units.code.java.IJavaIdentifierManager;
import com.pnfsoftware.jeb.core.units.code.java.IJavaType;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerCustomInitPostGraph;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Ser
public class bnd implements IJavaIdentifierManager {
   private static final ILogger q = GlobalLog.getLogger(bnd.class);
   @SerId(value = 1, deprecated = true)
   private boolean RF;
   @SerId(2)
   private boh xK;
   @SerId(3)
   private Map Dw;
   @SerId(value = 4, deprecated = true)
   private Map Uv;
   @SerId(5)
   private Map oW;
   @SerId(6)
   private int gO;

   @SerCustomInitPostGraph
   private void RF() {
      if (this.oW == null) {
         this.oW = new HashMap();
      }
   }

   public bnd(int var1) {
      switch (var1) {
         case 0:
            this.xK = new bog();
            break;
         case 1:
            this.xK = new bok();
            break;
         default:
            this.xK = new bog();
      }

      this.Dw = new HashMap();
      this.oW = new HashMap();
   }

   public void q() {
      this.xK.q();
      this.Dw.clear();
      this.oW.clear();
   }

   @Override
   public IJavaDefinition createDefinition(IJavaType var1, String var2) {
      int var3 = 1048576 + this.gO;
      this.gO++;
      return this.q(-2, var1, var3, -1, var2, null, -1, -1);
   }

   public IJavaDefinition q(int var1, IJavaType var2, int var3, int var4, String var5, String var6, int var7, int var8) {
      IJavaDefinition var9 = (IJavaDefinition)this.oW.get(var3);
      if (var9 != null) {
         return var9;
      } else {
         if (Strings.isBlank(var5)) {
            var5 = null;
         }

         if (Strings.isBlank(var6)) {
            var6 = null;
         }

         boolean var10 = var5 == null || var1 == 2;
         if (var5 == null) {
            var5 = this.xK.q(var1 == -1, var2, var3, var4);
         }

         if (var10 && this.Dw.containsKey(var5)) {
            String var11 = var5;
            boolean var12 = Character.isDigit(var5.charAt(var5.length() - 1));
            int var13 = 1;

            do {
               var5 = var11 + (var12 ? "_" : "") + var13;
               var13++;
            } while (this.Dw.containsKey(var5));
         }

         if (var4 >= 65536) {
            var4 = 65536 + this.gO;
            this.gO++;
         }

         IdentifierCoordinates var15 = null;
         if (var7 >= 0) {
            Object var16 = var8 < 0 ? new MethodCoordinates(var7) : new InstructionCoordinates(var7, var8);
            var15 = new IdentifierCoordinates((ICodeCoordinates)var16, var4);
         }

         bmx var14 = new bmx(var1, var2, var4, var15, var5, var6);
         var14.setPhysicalOffset(var8);
         IJavaIdentifier var17 = var14.getIdentifier();
         var17.setPhysicalOffset(var8);
         this.Dw.put(var5, var17);
         this.oW.put(var3, var14);
         return var14;
      }
   }

   @Override
   public Collection getIdentifiers() {
      return Collections.unmodifiableCollection(this.Dw.values());
   }

   @Override
   public IJavaDefinition getDefinition(IJavaIdentifier var1) {
      return !(var1 instanceof bnc) ? null : (IJavaDefinition)this.oW.get(((bnc)var1).Dw());
   }

   @Override
   public IJavaDefinition getDefinition(int var1) {
      return (IJavaDefinition)this.oW.get(var1);
   }

   @Override
   public IJavaIdentifier getIdentifier(String var1) {
      return (IJavaIdentifier)this.Dw.get(var1);
   }
}
