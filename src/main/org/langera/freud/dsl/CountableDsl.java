package org.langera.freud.dsl;

/**
 *   This file is part of "Freud".
 *
 *   Freud is free software: you can redistribute it and/or modify
 *   it under the terms of the GNU Lesser General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version.
 *
 *   Freud is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   GNU General Public License for more details.
 *
 *   You should have received a copy of the GNU General Public License
 *   along with Freud.  If not, see <http://www.gnu.org/licenses/>.
 *
 *   @author Amir Langer  langera_at_gmail_dot_com
**/

public interface CountableDsl<ThisDsl extends BooleanOperatorDsl>
{
    // TODO CountableDsl<ThisDsl> min(CountableDsl<ThisDsl>... values);

    // TODO CountableDsl<ThisDsl> max(CountableDsl<ThisDsl>... values);

    CountableDsl<ThisDsl> add(int value);

    CountableDsl<ThisDsl> add(CountableDsl<ThisDsl> value);

    CountableDsl<ThisDsl> subtract(int value);

    CountableDsl<ThisDsl> subtract(CountableDsl<ThisDsl> value);

    CountableDsl<ThisDsl> multiply(int value);

    CountableDsl<ThisDsl> multiply(CountableDsl<ThisDsl> value);

    BooleanOperatorDsl<ThisDsl> equalTo(int value);

    BooleanOperatorDsl<ThisDsl> equalTo(CountableDsl<ThisDsl> value);

    BooleanOperatorDsl<ThisDsl> lessThanOrEqualTo(int value);

    BooleanOperatorDsl<ThisDsl> lessThanOrEqualTo(CountableDsl<ThisDsl> value);

    BooleanOperatorDsl<ThisDsl> greaterThanOrEqualTo(int value);

    BooleanOperatorDsl<ThisDsl> greaterThanOrEqualTo(CountableDsl<ThisDsl> value);

    BooleanOperatorDsl<ThisDsl> lessThan(int value);

    BooleanOperatorDsl<ThisDsl> lessThan(CountableDsl<ThisDsl> value);

    BooleanOperatorDsl<ThisDsl> greaterThan(int value);

    BooleanOperatorDsl<ThisDsl> greaterThan(CountableDsl<ThisDsl> value);
}
