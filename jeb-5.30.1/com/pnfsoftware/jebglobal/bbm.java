package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.type.IEnumerationType;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Ser
public class bbm extends bbx implements IEnumerationType {
   @SerId(1)
   List q = new ArrayList();

   bbm(bby var1, String var2, String var3) {
      super(var1, var2, var3);
   }

   @Override
   public int getSize() {
      return this.xK().xK().q("int").getSize();
   }

   @Override
   public List getConstants() {
      return Collections.unmodifiableList(this.q);
   }

   public void q(String var1) {
      int var2 = this.oW();
      this.q(var1, var2);
   }

   public void q(String var1, int var2) {
      this.Me();
      this.q.add(new bbl(var1, var2));
   }

   private int oW() {
      return this.q.isEmpty() ? 0 : ((bbl)this.q.get(this.q.size() - 1)).getValue() + 1;
   }

   public bbl RF(String var1) {
      for (bbl var3 : this.q) {
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
