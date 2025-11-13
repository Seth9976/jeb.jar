package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.type.IEnumerationType;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Ser
public class aym extends ayx implements IEnumerationType {
   @SerId(1)
   List pC = new ArrayList();

   aym(ayy var1, String var2, String var3) {
      super(var1, var2, var3);
   }

   @Override
   public int getSize() {
      return this.kS().pC().pC("int").getSize();
   }

   @Override
   public List getConstants() {
      return Collections.unmodifiableList(this.pC);
   }

   public void pC(String var1) {
      int var2 = this.E();
      this.pC(var1, var2);
   }

   public void pC(String var1, int var2) {
      this.vP();
      this.pC.add(new ayl(var1, var2));
   }

   private int E() {
      return this.pC.isEmpty() ? 0 : ((ayl)this.pC.get(this.pC.size() - 1)).getValue() + 1;
   }

   public ayl A(String var1) {
      for (ayl var3 : this.pC) {
         if (var3.getName().equals(var1)) {
            return var3;
         }
      }

      return null;
   }

   @Override
   public String toString() {
      return Strings.ff("EnumType(%s)%s", this.getSignature(false), Strings.joinList(this.getConstants()));
   }
}
