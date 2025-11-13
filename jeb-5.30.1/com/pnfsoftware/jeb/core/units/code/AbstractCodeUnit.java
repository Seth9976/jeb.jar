package com.pnfsoftware.jeb.core.units.code;

import com.pnfsoftware.jeb.core.IUnitCreator;
import com.pnfsoftware.jeb.core.input.IInput;
import com.pnfsoftware.jeb.core.properties.IPropertyDefinitionManager;
import com.pnfsoftware.jeb.core.units.AbstractInteractiveBinaryUnit;
import com.pnfsoftware.jeb.core.units.IUnitProcessor;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import java.util.Collections;
import java.util.List;

@Ser
public abstract class AbstractCodeUnit extends AbstractInteractiveBinaryUnit implements ICodeUnit {
   public AbstractCodeUnit(String var1, String var2, IInput var3, IUnitProcessor var4, IUnitCreator var5, IPropertyDefinitionManager var6) {
      super(null, var3, var2, var1, var4, var5, var6);
   }

   @Override
   public List getStrings() {
      return Collections.emptyList();
   }

   @Override
   public List getPackages() {
      return Collections.emptyList();
   }

   @Override
   public List getTypes() {
      return Collections.emptyList();
   }

   @Override
   public List getClasses() {
      return Collections.emptyList();
   }

   @Override
   public List getFields() {
      return Collections.emptyList();
   }

   @Override
   public List getMethods() {
      return Collections.emptyList();
   }

   @Override
   public ICodeClass getClass(String var1) {
      return null;
   }

   @Override
   public ICodeField getField(String var1) {
      return null;
   }

   @Override
   public ICodeMethod getMethod(String var1) {
      return null;
   }
}
