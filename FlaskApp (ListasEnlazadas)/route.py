from flask import render_template, request, redirect, url_for, flash
import json
import os
from datetime import datetime
from app import app, FAMILIAS_FILE, GENERADORES_FILE, TRANSACCIONES_FILE

def load_json(file_path):
    if os.path.exists(file_path):
        with open(file_path, 'r') as file:
            return json.load(file)
    return []

def save_json(data, file_path):
    os.makedirs(os.path.dirname(file_path), exist_ok=True)
    with open(file_path, 'w') as file:
        json.dump(data, file, indent=4)

# Ruta principal
@app.route('/')
def index():
    return render_template('index.html')

# Ruta de Familia
@app.route('/familia', methods=['GET', 'POST'])
def familia():
    if request.method == 'POST':
        familias = load_json(FAMILIAS_FILE)
        nueva_familia = {
            'id': len(familias) + 1,
            'nombre': request.form['nombre'],
            'numeroMiembros': int(request.form['numeroMiembros']),
            'generadorAdquirido': request.form.get('generadorAdquirido') == 'on'
        }
        familias.append(nueva_familia)
        save_json(familias, FAMILIAS_FILE)
        flash('Familia registrada exitosamente')
        return redirect(url_for('familia'))
    return render_template('familia_form.html')

# Ruta para Generador
@app.route('/generador', methods=['GET', 'POST'])
def generador():
    if request.method == 'POST':
        generadores = load_json(GENERADORES_FILE)
        nuevo_generador = {
            'id': len(generadores) + 1,
            'marca': request.form['marca'],
            'modelo': request.form['modelo'],
            'costo': float(request.form['costo']),
            'consumoPorHora': float(request.form['consumoPorHora']),
            'generacion': float(request.form['generacion']),
            'uso': request.form['uso']
        }
        generadores.append(nuevo_generador)
        save_json(generadores, GENERADORES_FILE)
        flash('Generador registrado exitosamente')
        return redirect(url_for('generador'))
    return render_template('generador_form.html')

# Ruta para Transaccion
@app.route('/transaccion', methods=['GET', 'POST'])
def transaccion():
    if request.method == 'POST':
        transacciones = load_json(TRANSACCIONES_FILE)
        nueva_transaccion = {
            'id': len(transacciones) + 1,
            'fecha': datetime.now().strftime('%Y-%m-%d %H:%M:%S'),
            'familiaId': int(request.form['familiaId']),
            'generadorId': int(request.form['generadorId']),
            'tipo': request.form['tipo']
        }
        transacciones.append(nueva_transaccion)
        save_json(transacciones, TRANSACCIONES_FILE)
        flash('Transacción registrada exitosamente')
        return redirect(url_for('transaccion'))
    familias = load_json(FAMILIAS_FILE)
    generadores = load_json(GENERADORES_FILE)
    return render_template('transaccion_form.html', familias=familias, generadores=generadores)

@app.route('/estadisticas')
def estadisticas():
    # Cargar datos de los archivos JSON
    familias = load_json(FAMILIAS_FILE)
    generadores = load_json(GENERADORES_FILE)
    transacciones = load_json(TRANSACCIONES_FILE)

    # Estadísticas básicas
    total_familias = len(familias)
    familias_con_generador = sum(1 for f in familias if f.get('generadorAdquirido', False))
    total_transacciones = len(transacciones)

    # Estadísticas de generadores por marca
    generadores_por_marca = {}
    for gen in generadores:
        marca = gen['marca']
        if marca not in generadores_por_marca:
            generadores_por_marca[marca] = {
                'cantidad': 0,
                'costo_total': 0,
                'generacion_total': 0,
                'consumo_total': 0
            }
        stats = generadores_por_marca[marca]
        stats['cantidad'] += 1
        stats['costo_total'] += gen['costo']
        stats['generacion_total'] += gen['generacion']
        stats['consumo_total'] += gen['consumoPorHora']

    # Preparar estadísticas de generadores para la tabla
    generadores_stats = []
    for marca, stats in generadores_por_marca.items():
        generadores_stats.append({
            'marca': marca,
            'cantidad': stats['cantidad'],
            'costo_promedio': stats['costo_total'] / stats['cantidad'],
            'generacion_promedio': stats['generacion_total'] / stats['cantidad'],
            'consumo_promedio': stats['consumo_total'] / stats['cantidad']
        })

    # Datos para los gráficos
    marcas = list(generadores_por_marca.keys())
    cantidades_por_marca = [stats['cantidad'] for stats in generadores_por_marca.values()]

    return render_template('estadisticas.html',
                         total_familias=total_familias,
                         familias_con_generador=familias_con_generador,
                         total_transacciones=total_transacciones,
                         generadores_stats=generadores_stats,
                         marcas=marcas,
                         cantidades_por_marca=cantidades_por_marca)