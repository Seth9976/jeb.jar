package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.input.IDataProvider;
import com.pnfsoftware.jeb.core.units.impl.LazyDataContainerUnit;
import java.util.Map;

public abstract class cku extends ckt {
   String A;
   long kS;

   public void pC(String var1, long var2) {
      this.A = var1;
      this.kS = var2;
   }

   public LazyDataContainerUnit.Entry pC(String var1) {
      return LazyDataContainerUnit.Entry.createFolder(var1);
   }

   public LazyDataContainerUnit.Entry pC(String var1, int var2) {
      return LazyDataContainerUnit.Entry.create(this.A(), this.A, this.kS, var1, var2);
   }

   public abstract IDataProvider A();

   public Map pC(LazyDataContainerUnit.Entry var1) {
      return var1.getChildren();
   }

   public boolean A(LazyDataContainerUnit.Entry var1) {
      return var1.isFolder();
   }
}
