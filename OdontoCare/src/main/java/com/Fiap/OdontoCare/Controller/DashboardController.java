package com.Fiap.OdontoCare.Controller;

import com.Fiap.OdontoCare.Entity.Consulta;
import com.Fiap.OdontoCare.Service.ConsultaService;
import com.Fiap.OdontoCare.Service.DentistaService;
import com.Fiap.OdontoCare.Service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/dashboard")
public class DashboardController {

    @Autowired
    private PacienteService pacienteService;

    @Autowired
    private DentistaService dentistaService;

    @Autowired
    private ConsultaService consultaService;

    @GetMapping
    public String mostrarDashboard(Model model) {
        model.addAttribute("totalPacientes", pacienteService.count());
        model.addAttribute("totalDentistas", dentistaService.count());
        model.addAttribute("totalConsultas", consultaService.count());

        List<Consulta> consultas = consultaService.findAll();
        model.addAttribute("consultas", consultas);

        return "dashboard";
    }

    @PostMapping("/excluir/{id}")
    public String excluirConsulta(@PathVariable Long id) {
        consultaService.deleteById(id);
        return "redirect:/dashboard";
    }
}
