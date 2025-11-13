package com.pnfsoftware.jeb.core.units.code.android.ir;

import java.util.List;

public interface IDNewArrayInfo extends IDInvokeInfo {
   IDExpression getSize();

   void setSize(IDExpression var1);

   int getCountOfInitialValues();

   List getInitialValues();

   IDExpression getInitialValue(int var1);

   void setInitialValues(List var1);

   void setInitialValue(int var1, IDExpression var2);

   boolean areSubExpsAllImms();

   IDNewArrayInfo duplicate();
}
