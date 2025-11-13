package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.input.IDataProvider;
import com.pnfsoftware.jeb.core.units.impl.LazyDataContainerUnit;
import java.util.Map;

public abstract class cvj extends cvi {
   String RF;
   long xK;

   public void q(String var1, long var2) {
      this.RF = var1;
      this.xK = var2;
   }

   public LazyDataContainerUnit.Entry q(String var1) {
      return LazyDataContainerUnit.Entry.createFolder(var1);
   }

   public LazyDataContainerUnit.Entry q(String var1, int var2) {
      return LazyDataContainerUnit.Entry.create(this.RF(), this.RF, this.xK, var1, var2);
   }

   public abstract IDataProvider RF();

   public Map q(LazyDataContainerUnit.Entry var1) {
      return var1.getChildren();
   }

   public boolean RF(LazyDataContainerUnit.Entry var1) {
      return var1.isFolder();
   }
}
