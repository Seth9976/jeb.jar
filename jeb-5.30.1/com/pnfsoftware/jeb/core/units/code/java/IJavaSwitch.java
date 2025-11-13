package com.pnfsoftware.jeb.core.units.code.java;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;

@Ser
public interface IJavaSwitch extends IJavaCompound {
   int getSwitchType();

   boolean isSwitchOnInteger();

   boolean isSwitchOnString();

   boolean isSwitchOnEnum();

   Map getStringMap();

   Map getEnumMap();

   IJavaExpression getSwitchedExpression();

   void setSwitchedExpression(IJavaExpression var1);

   SortedSet getCaseKeys();

   List getCaseBodies();

   IJavaBlock getCaseBody(int var1);

   IJavaBlock getCaseBody(String var1);

   boolean hasDefaultBlock();

   IJavaBlock getDefaultBlock();

   void addCase(List var1, IJavaBlock var2);

   void addCase(Map var1, IJavaBlock var2);

   void setDefaultBlock(IJavaBlock var1);

   @Override
   boolean replaceSubElement(IJavaElement var1, IJavaElement var2);

   void convertToSwitchOnInteger(IJavaExpression var1);

   void convertToSwitchOnString(IJavaExpression var1, Map var2);

   void convertToSwitchOnEnum(IJavaExpression var1, Map var2);

   IJavaSwitch duplicate();

   void reset(int var1);
}
