/*
 * Copyright 2017 HM Revenue & Customs
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package unit.uk.gov.hmrc.testuser.services

import uk.gov.hmrc.play.test.UnitSpec
import uk.gov.hmrc.testuser.services.PasswordService

class PasswordServiceSpec extends UnitSpec{

  trait Setup {
    val underTest = new PasswordService {
      override val logRounds: Int = 12
    }
  }

  "hash" should {
    "return the hashed password" in new Setup {

      val hashedPassword = underTest.hash("aPassword")

      hashedPassword should not be "aPassword"
      underTest.validate("aPassword", hashedPassword) shouldBe true
    }
  }

  "validate" should {
    "return true when the password is correct" in new Setup {

      val hashedPassword = underTest.hash("aPassword")

      underTest.validate("aPassword", hashedPassword) shouldBe true
    }

    "return false when the password is incorrect" in new Setup {

      val anotherHashedPassword = underTest.hash("anotherPassword")

      underTest.validate("aPassword", anotherHashedPassword) shouldBe false
    }

  }

}
