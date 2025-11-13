package com.pnfsoftware.jeb.core.units.code.java;

import com.pnfsoftware.jeb.core.output.code.coordinates.IdentifierCoordinates;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;

@Ser
public interface IJavaDefinition extends IJavaLeftExpression, IJavaStatement {
   IJavaType getType();

   IdentifierCoordinates getCoordinates();

   IJavaIdentifier getIdentifier();

   IJavaDefinition duplicate();
}
