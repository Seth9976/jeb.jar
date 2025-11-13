package com.pnfsoftware.jeb.core.units.code.android.ir;

import java.util.List;

public interface IDInvokeInfo extends IDExpression {
   List getArguments();

   IDInvokeInfo duplicate();
}
