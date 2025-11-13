package com.pnfsoftware.jeb.core.units.code;

import java.util.List;

public interface ICodePackage extends ICodeItem {
   boolean isRootPackage();

   ICodePackage getParentPackage();

   List getChildrenPackages();

   List getChildren();
}
