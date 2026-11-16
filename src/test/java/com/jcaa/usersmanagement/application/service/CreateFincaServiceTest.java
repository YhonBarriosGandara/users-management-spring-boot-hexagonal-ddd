package com.jcaa.usersmanagement.application.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import com.jcaa.usersmanagement.application.port.out.SaveFincaPort;
import com.jcaa.usersmanagement.application.service.dto.command.CreateFincaCommand;
import com.jcaa.usersmanagement.domain.model.FincaModel;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
@DisplayName("CreateFincaService")
class CreateFincaServiceTest {

  @Mock private SaveFincaPort saveFincaPort;

  private Validator validator;
  private CreateFincaService service;

  @BeforeEach
  void setUp() {
    validator = Validation.buildDefaultValidatorFactory().getValidator();
    service = new CreateFincaService(saveFincaPort, validator);
  }

  @Test
  @DisplayName("execute() debe crear y guardar la Finca correctamente")
  void shouldCreateAndSaveFinca() {
    final CreateFincaCommand command =
        new CreateFincaCommand(
            "f-001",
            "Hacienda San Pedro",
            50.0,
            500000.0,
            "Carlos Gómez",
            "Manuel Santos",
            "Colombia",
            "Antioquia",
            "Rionegro",
            true,
            false,
            true,
            false);

    when(saveFincaPort.save(any(FincaModel.class)))
        .thenAnswer(invocation -> invocation.getArgument(0));

    final FincaModel result = service.execute(command);

    assertNotNull(result);
    assertEquals("f-001", result.getId().value());
    assertEquals("Hacienda San Pedro", result.getNombre().value());
    assertEquals(50.0, result.getSuperficie().numHectareas());
    assertEquals(500000.0, result.getSuperficie().metrosCuadrados());
    verify(saveFincaPort, times(1)).save(any(FincaModel.class));
  }
}
