package com.pnfsoftware.jeb.corei.testbed;

import com.pnfsoftware.jeb.core.units.IUnit;
import java.io.IOException;
import java.util.Map;

public interface nI {
   int q = 0;
   int RF = 10;
   int xK = 11;
   int Dw = 50;

   String q();

   int RF();

   Map q(IUnit var1);

   Map q(String var1) throws IOException;
}
