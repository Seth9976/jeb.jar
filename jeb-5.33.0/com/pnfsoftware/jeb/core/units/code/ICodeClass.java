package com.pnfsoftware.jeb.core.units.code;

import java.util.List;

public interface ICodeClass extends ICodeItem {
   ICodePackage getPackage();

   ICodeType getClassType();

   List getSupertypes();

   List getImplementedInterfaces();

   List getMethods();

   List getFields();
}
