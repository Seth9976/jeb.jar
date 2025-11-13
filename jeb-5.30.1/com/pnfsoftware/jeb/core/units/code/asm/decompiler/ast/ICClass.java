package com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import java.util.List;

@Ser
public interface ICClass extends ICDecompilableElement {
   ICType getClasstype();

   List getSupertypes();

   List getFieldAddresses();

   List getFields();

   void addField(ICField var1);

   void addField(int var1, ICField var2);

   boolean removeField(ICField var1);

   List getMethodAddresses();

   List getMethods();

   void addMethod(ICMethod var1);

   void addMethod(int var1, ICMethod var2);

   boolean removeMethod(ICMethod var1);

   ICClass duplicate();
}
