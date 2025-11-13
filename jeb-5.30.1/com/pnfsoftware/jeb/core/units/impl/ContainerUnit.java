package com.pnfsoftware.jeb.core.units.impl;

import com.pnfsoftware.jeb.core.IUnitCreator;
import com.pnfsoftware.jeb.core.properties.IPropertyDefinitionManager;
import com.pnfsoftware.jeb.core.units.AbstractUnit;
import com.pnfsoftware.jeb.core.units.IUnitProcessor;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Ser
public class ContainerUnit extends AbstractUnit {
   public static final Comparator CONTAINERS_FIRST = new ContainerUnit$1();

   public ContainerUnit(String var1, String var2, IUnitProcessor var3, IUnitCreator var4, IPropertyDefinitionManager var5) {
      super(var1, var2, var3, var4, var5);
   }

   public ContainerUnit(String var1, IUnitProcessor var2, IUnitCreator var3, IPropertyDefinitionManager var4) {
      this("composite", var1, var2, var3, var4);
   }

   @Override
   public boolean process() {
      return this.process(true);
   }

   public boolean process(boolean var1) {
      this.setProcessed(true);
      return true;
   }

   @Override
   public boolean equals(Object var1) {
      return !(var1 instanceof ContainerUnit var2)
         ? false
         : Strings.equals(var2.getFormatType(), this.getFormatType()) && Strings.equals(var2.getName(), this.getName());
   }

   @Override
   public int hashCode() {
      return super.hashCode();
   }

   public ContainerUnit getRootContainer() {
      return this.getParent() instanceof ContainerUnit ? ((ContainerUnit)this.getParent()).getRootContainer() : this;
   }

   @Override
   public List getChildren() {
      ArrayList var1 = new ArrayList(super.getChildren());
      Collections.sort(var1, CONTAINERS_FIRST);
      return var1;
   }
}
