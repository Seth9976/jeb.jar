package com.pnfsoftware.jeb.core.units.code.java;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import java.util.List;

@Ser
public interface IJavaNewArray extends IJavaExpression, IJavaStatement {
   IJavaType getType();

   List getSizes();

   boolean hasInitialValues();

   List getInitialValues();

   IJavaNewArray duplicate();
}
